package essthsapp.ihebchiha.com.essthsapp.ftp;

import android.content.Intent;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileChooser;

import java.io.File;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Adapters.FileAdapter;
import essthsapp.ihebchiha.com.essthsapp.Adapters.FileTap;
import essthsapp.ihebchiha.com.essthsapp.Models.FtpFile;
import essthsapp.ihebchiha.com.essthsapp.R;

public class FilesActivity extends AppCompatActivity implements FileTap {

    final  int PICK_FILE_REQUEST=100;
    private FTPUploader ftpclient = null;
    List<FtpFile> ftpFiles;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_files);

        recyclerView =findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);


        //Button btn = (Button) findViewById(R.id.button);


        ftpclient = new FTPUploader();

        connect();
        getRemoteFile();


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFiles();
            }
        });

    }



     /*   btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
getFiles();
               *//* new Thread(new Runnable() {
                    public void run() {
                        connect();
                    }
                }).start();
            }
        });*//*
    }*/


    public void getFiles(){

        Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
        i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.MULTIPLE_SELECTION.ordinal());
        startActivityForResult(i2,PICK_FILE_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                boolean   status = ftpclient.ftpConnect("192.168.1.9", "ftp_user", "iheb123456", 21);
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
        Toast.makeText(this,"Start download " +ftpFile.getName(), Toast.LENGTH_SHORT).show();
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

        Toast.makeText(getApplicationContext(), "Deleted file!", Toast.LENGTH_SHORT).show();
        getRemoteFile();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
