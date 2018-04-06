package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.Config.ConfigRetrofit;
import essthsapp.ihebchiha.com.essthsapp.Functions.IUser;
import essthsapp.ihebchiha.com.essthsapp.LoginActivity;
import essthsapp.ihebchiha.com.essthsapp.MenuActivity;
import essthsapp.ihebchiha.com.essthsapp.Modules.User;
import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView fname,lname;
    ImageView pimg;
    Button logout;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        fname=rootView.findViewById(R.id.fnametxt);
        lname=rootView.findViewById(R.id.lnametxt);
        pimg= rootView.findViewById(R.id.profileImg);
        logout=rootView.findViewById(R.id.logoutBtn);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        fname.setText(preferences.getString("fName","-1"));
        lname.setText(preferences.getString("lName","-1"));
        String cin=preferences.getString("cin","0");
        IUser iUser= ConfigRetrofit.retrofit.create(IUser.class);
        Call<User> call = iUser.getFoto(cin);
        call.enqueue(new Callback<User>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body()!=null)
                {
                    String encodedString=response.body().getImglink();
                    byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    pimg.setImageBitmap(decodedByte);
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Some errors may occured", Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsSharedPreferences.saveSharedSetting(Objects.requireNonNull(getContext()), "PassingThrough", "true");
                UtilsSharedPreferences.SharedPrefesSAVE(getContext(), "");
                Intent LogOut = new Intent(getActivity(), LoginActivity.class);
                startActivity(LogOut);
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        return rootView;
    }

}
