package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.LoginActivity;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView fname,lname;
    Button logout;


    //ContentResolver contentResolver=getContentResolver();

    private int PICK_IMAGE_REQUEST = 1;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        fname=rootView.findViewById(R.id.fnametxt);
        lname=rootView.findViewById(R.id.lnametxt);
        logout=rootView.findViewById(R.id.logoutBtn);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        fname.setText(preferences.getString("fName","-1"));
        lname.setText(preferences.getString("lName","-1"));
        String cin=preferences.getString("cin","0");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());
                // set title
                alertDialogBuilder.setTitle("Information");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Voulez vous déconnecter ?")
                        .setCancelable(false)
                        .setNegativeButton("non",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UtilsSharedPreferences.saveSharedSetting(Objects.requireNonNull(getContext()), "PassingThrough", "true");
                        UtilsSharedPreferences.SharedPrefesSAVE(getContext(), "");
                        Intent LogOut = new Intent(getActivity(), LoginActivity.class);
                        startActivity(LogOut);
                        Objects.requireNonNull(getActivity()).finish();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        return rootView;
    }

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri);
                // Log.d(TAG, String.valueOf(bitmap));
               // pimg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
