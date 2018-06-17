package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.LoginActivity;
import essthsapp.ihebchiha.com.essthsapp.ProfileEditActivity;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.UserSessionManager;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView fname,lname;
    Button logout,edit;
    UserSessionManager session;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        fname=rootView.findViewById(R.id.fnametxt);
        lname=rootView.findViewById(R.id.lnametxt);
        logout=rootView.findViewById(R.id.logoutBtn);
        edit=rootView.findViewById(R.id.editbtn);
        session = new UserSessionManager(getContext());
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
                        session.logoutUser();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileEditActivity.class));
            }
        });
        return rootView;
    }


}
