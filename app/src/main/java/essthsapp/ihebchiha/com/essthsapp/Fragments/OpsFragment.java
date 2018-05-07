package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.Adapters.MenuFileAdapter;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.extras.FileItem;
import essthsapp.ihebchiha.com.essthsapp.ftp.MyFTPClientFunctions;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpsFragment extends Fragment{

    MyFTPClientFunctions ftpclient=null;
    Button connectbtn;
    private ProgressDialog pd;
    private static final String TEMP_FILENAME = "TAGtest.txt";
    private String[] fileList;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            if (msg.what == 0) {
                getFTPFileList();
            } else if (msg.what == 1) {
                showCustomDialog(fileList);
            } else if (msg.what == 2) {
                Toast.makeText(getActivity(), "Uploaded Successfully!",
                        Toast.LENGTH_LONG).show();
            } else if (msg.what == 3) {
                Toast.makeText(getActivity(), "Disconnected Successfully!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Unable to Perform Action!",
                        Toast.LENGTH_LONG).show();
            }

        }

    };
    public OpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_ops, container, false);
        connectbtn=rootview.findViewById(R.id.upload);

        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline(Objects.requireNonNull(getActivity()))) {
                    connectToFTPAddress();
                } else {
                    Toast.makeText(getActivity(),
                            "Please check your internet connection!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        ftpclient=new MyFTPClientFunctions();
        return rootview;
    }

    private void connectToFTPAddress() {

        final String host = "192.168.1.6";
        final String username = "ftp_user";
        final String password = "iheb123456";

        if (host.length() < 1) {
            Toast.makeText(getActivity(), "Please Enter Host Address!",
                    Toast.LENGTH_LONG).show();
        } else if (username.length() < 1) {
            Toast.makeText(getActivity(), "Please Enter User Name!",
                    Toast.LENGTH_LONG).show();
        } else if (password.length() < 1) {
            Toast.makeText(getActivity(), "Please Enter Password!",
                    Toast.LENGTH_LONG).show();
        } else {

            pd = ProgressDialog.show(getActivity(), "", "Connecting...",
                    true, false);

            new Thread(new Runnable() {
                public void run() {
                    boolean status = false;
                    status = ftpclient.ftpConnect(host, username, password, 21);
                    if (status) {
                        Log.d(TAG, "Connection Success");
                        handler.sendEmptyMessage(0);
                    } else {
                        Log.d(TAG, "Connection failed");
                        handler.sendEmptyMessage(-1);
                    }
                }
            }).start();
        }
    }

    private void getFTPFileList() {
        pd = ProgressDialog.show(getActivity(), "", "Getting Files...",
                true, false);

        new Thread(new Runnable() {

            @Override
            public void run() {
                fileList = ftpclient.ftpPrintFilesList("/");
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    public void createDummyFile() {

        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    "TAGFtp");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, TEMP_FILENAME);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("This is a test!");
            writer.flush();
            writer.close();
            Toast.makeText(getActivity(), "Saved : " + gpxfile.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void showCustomDialog(String[] fileList) {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("/ Directory File List");

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvListHeading);
        tvHeading.setText(":: File List ::");

        if (fileList != null && fileList.length > 0) {
            ListView listView = (ListView) dialog
                    .findViewById(R.id.lstItemList);
            ArrayAdapter<String> fileListAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1, fileList);
            listView.setAdapter(fileListAdapter);
        } else {
            tvHeading.setText(":: No Files ::");
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.btnOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    }

