package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Adapters.MenuFileAdapter;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.extras.FileItem;

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

        MenuFileAdapter adapter = null;
        try {
            adapter = new MenuFileAdapter(getContext(), getData(), new MenuFileAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) throws IOException {
                    connect("172.16.51.72","ftp_user","iheb123456");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


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
   /* public static void connectingwithFTP(String ip, String userName, String pass,FTPFile[] mFileArray) {
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
    }*/
    public List<FileItem> getData() throws IOException {
        List<FileItem> fileItemList=new ArrayList<>();
        List<FTPFile> fileList;
        fileList=connect("172.16.51.72","ftp_user","iheb123456");
        FileItem current = new FileItem();
        for (int i=0;i<fileList.size();i++) {
            current.txt = fileList.get(i).getName();
            fileItemList.add(current);
        }
        return fileItemList;
    }

    private List<FTPFile> connect(String ip, String user, String pass) throws IOException {
        FTPClient ftpClient=new FTPClient();
        ftpClient.setConnectTimeout(10 * 1000);
        ftpClient.connect(InetAddress.getByName(ip));
        boolean status=ftpClient.login(user,pass);
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            boolean m=ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            Log.e("Size", String.valueOf(Arrays.asList(ftpClient.listFiles()).size()));

        }
        return Arrays.asList(ftpClient.listFiles());
    }
   private void listAllFiles(String path) throws IOException // path is the top folder to start the search
    {
        FTPClient ftpClient=new FTPClient();
         FTPFile[] files = ftpClient.listFiles(path); // Search all the files in the current directory
         for (int j = 0; j < files.length; j++) {
        Log.d("CONNECT", "Files: " + files[j].getName()); // Print the name of each files
    }

        FTPFile[] directories = ftpClient.listDirectories(path); // Search all the directories in the current directory

         for (int i = 0; i < directories.length; i++) {
        String dirPath = directories[i].getName();
        Log.d("CONNECT", "Directories: "+ dirPath); // Print the path of a sub-directory
        listAllFiles(dirPath); // Call recursively the method to display the files in the sub-directory
    }
}

    }

