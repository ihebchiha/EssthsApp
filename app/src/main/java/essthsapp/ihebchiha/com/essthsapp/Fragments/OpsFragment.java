package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Adapters.MenuFileAdapter;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.extras.FileItem;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpsFragment extends Fragment implements RecyclerView.OnItemTouchListener{
    FTPFile file;
    RecyclerView mRv;
    FTPFile [] farray;
    public OpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_ops, container, false);
        mRv =rootview.findViewById(R.id.myRec); //mList => id de RecyclerView
        MenuFileAdapter adapter = new MenuFileAdapter(getContext(), getData(), new MenuFileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // connectingwithFTP("192.168.1.4","ftp_user","iheb123456",farray);

            }
        });


        if (mRv != null) {
            mRv.setAdapter(adapter);
            mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return rootview;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    //Ftp Functions
    public static void connectingwithFTP(String ip, String userName, String pass,FTPFile[] mFileArray) {
        boolean status = false;
        try {

            FTPClient mFtpClient = new FTPClient();
            mFtpClient.setConnectTimeout(10 * 1000);
            mFtpClient.connect(InetAddress.getByName(ip));
            status = mFtpClient.login(userName, pass);
            Log.e("isFTPConnected", String.valueOf(status));
            if (FTPReply.isPositiveCompletion(mFtpClient.getReplyCode())) {
                mFtpClient.setFileType(FTP.ASCII_FILE_TYPE);
                mFtpClient.enterLocalPassiveMode();
                mFileArray = mFtpClient.listFiles();
                Log.e("Size", String.valueOf(mFileArray.length));

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<FileItem> getData() {
        List<FileItem> list = new ArrayList<>();
        //FTPFile[] mTab= new FTPFile[]{};
        //connectingwithFTP("192.168.1.8","ftp_user","iheb123456",mTab);
        String mTab[]={"iheb","chiha","iheb","khalil"};
        for (String aMTab : mTab) {
            FileItem current = new FileItem();
            current.txt = aMTab;
            list.add(current);

        }
        return list;
    }

    public boolean downloadSingleFile(FTPClient ftpClient,
                                      String remoteFilePath, File downloadFile) {
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())
            parentDir.mkdir();
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(
                    downloadFile));
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
