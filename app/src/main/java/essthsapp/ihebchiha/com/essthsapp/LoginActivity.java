package essthsapp.ihebchiha.com.essthsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.chooser.ChooserTarget;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Config.ConfigRetrofit;
import essthsapp.ihebchiha.com.essthsapp.Functions.IUser;
import essthsapp.ihebchiha.com.essthsapp.Modules.User;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    EditText user,passw;
    TextView forgot;
    Button connect;
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linearLayout = (LinearLayout)findViewById(R.id.layout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        user=findViewById(R.id.usernametxt);
        passw=findViewById(R.id.cintxt);
        forgot=findViewById(R.id.forgotlbl);
        connect=findViewById(R.id.connectBtn);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* UtilsSharedPreferences.saveSharedSetting(LoginActivity.this, "LoggingIn", "false");
                UtilsSharedPreferences.SharedPrefesSAVE(getApplicationContext(), user.getText().toString());
                Intent ImLoggedIn = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(ImLoggedIn);
                finish();*/

                String username=user.getText().toString();
                String password=passw.getText().toString();
                /*if (validateLogin(username,password))
                {
                IUser iUser= ConfigRetrofit.retrofit.create(IUser.class);
                Call<User> call=iUser.login(username,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body()!=null)
                        {
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("cin",response.body().getCin());
                            editor.putString("lName",response.body().getLname());
                            editor.putString("fName",response.body().getFname());
                            editor.putString("username",response.body().getUsername());
                           editor.apply();*/
                            Toast.makeText(LoginActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this ,MenuActivity.class));
                      /*  }else
                        {
                            Log.d("Error: ",response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Log.d("Erreur: ",t.getMessage());
                    }
                });
            }*/
        }
    });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] poles={"iheb123@outlook.com","ihebchiha11@protonmail.com"};
                Intent intent =new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,poles);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Password Recovery");
                intent.putExtra(Intent.EXTRA_TEXT,"Dear Mr Iheb Chiha, You demamnded to recover your password. " +
                        "Your request is accepted and this is your PASSWORD: "+" wiwcity");
                intent.setType("message/rfc822");
                Intent chooser=Intent.createChooser(intent,"Send Email");
                startActivity(chooser);
            }
        });
    }
    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Nom utilisateur requis", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Mot de Passe requise", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }
}
