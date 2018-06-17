package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileChooser;

import java.io.File;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Adapters.FileAdapter;
import essthsapp.ihebchiha.com.essthsapp.Adapters.FileTap;
import essthsapp.ihebchiha.com.essthsapp.Models.FtpFile;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.ftp.FTPUploader;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpsFragment extends Fragment implements FileTap{

    final  int PICK_FILE_REQUEST=100;
    private FTPUploader ftpclient = null;
    List<FtpFile> ftpFiles;
    RecyclerView recyclerView;
    Button uploadbtn,downloadbtn;

    public OpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_ops, container, false);
        /*downloadbtn=rootview.findViewById(R.id.download);
        uploadbtn=rootview.findViewById(R.id.upload);
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getActivity(), FilesActivity.class));
            }
        });*/
        recyclerView =rootview.findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);


        //Button btn = (Button) findViewById(R.id.button);


        ftpclient = new FTPUploader();

        connect();
        getRemoteFile();


        FloatingActionButton floatingActionButton = (FloatingActionButton)rootview.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFiles();
            }
        });
        return rootview;
    }
    public void getFiles(){

        Intent i2 = new Intent(getContext(), FileChooser.class);
        i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.MULTIPLE_SELECTION.ordinal());
        startActivityForResult(i2,PICK_FILE_REQUEST);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST && data != null) {
            if (resultCode == RESULT_OK) {
                List<Uri> selectedFiles = data.getParcelableArrayListExtra(Constants.SELECTED_ITEMS);
                upload(selectedFiles);
              /*  for(Uri uri:selectedFiles){
                    Log.e("uri=",uri.toString());

                    // upload()
                }*/
            }

        }



        //boolean   status = ftpclient.ftpConnect("192.168.1.55", username, password, 21);


    }

    public void upload(final List<Uri> selectedFiles){
        // file:///storage/emulated/0/Abfa/export.json

        Thread tr=     new Thread(new Runnable() {
            public void run() {

                for(Uri uri:selectedFiles) {
                    File file= new File(uri.getPath());
                    String name= file.getName();
                    ftpclient.ftpUpload(uri.getPath(),name);

                }
            }
        });
        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getRemoteFile();
    }
    private void connect(){

        Thread tr=     new Thread(new Runnable() {
            public void run() {
                boolean   status = ftpclient.ftpConnect("192.168.1.7", "ftp_user", "iheb123456", 21);
                //boolean   status = ftpclient.ftpConnect("192.168.1.55", "shabab", "123456", 2221);

                Log.e("status","status"+status);
            }
        });
        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void getRemoteFile(){

        //  List<FtpFile>   ftpFiles=new ArrayList<>();
        Thread tr=new Thread(new Runnable() {
            @Override
            public void run() {
                ftpFiles=     ftpclient.getListOfFile();
//                 Log.e("hhh=",ftpFiles.get(0).getName());
                //    Log.e("hhh=",ftpFiles.get(1).getName());
            }
        });
        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ftpFiles.size()>0) {
            FileAdapter fileAdapter = new FileAdapter(ftpFiles, this);
            recyclerView.setAdapter(fileAdapter);
            fileAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onItemTap(final FtpFile ftpFile) {
        Toast.makeText(getActivity(),"Start download " +ftpFile.getName(), Toast.LENGTH_SHORT).show();
        Thread tr=new Thread(new Runnable() {
            @Override
            public void run() {
                ftpclient.downloadFile(ftpFile.getName());
            }
        });
        tr.start();
    }

    @Override
    public void onLongItemClick(final FtpFile ftpFile) {

        Thread tr=new Thread(new Runnable() {
            @Override
            public void run() {
                ftpclient.deleteFile(ftpFile.getName());
            }
        });
        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(getContext(), "Deleted file!", Toast.LENGTH_SHORT).show();
        getRemoteFile();


    }

    }

