package essthsapp.ihebchiha.com.essthsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.Config.ConfigRetrofit;
import essthsapp.ihebchiha.com.essthsapp.Functions.IUser;
import essthsapp.ihebchiha.com.essthsapp.Models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    EditText user,passw;
    Button connect;
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());
        linearLayout = findViewById(R.id.layout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        user=findViewById(R.id.usernametxt);
        passw=findViewById(R.id.passtxt);

        connect=findViewById(R.id.connectBtn);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=user.getText().toString();
                String password=passw.getText().toString();
                if (validateLogin(username,password))
                {
                IUser iUser= ConfigRetrofit.retrofit.create(IUser.class);
                Call<User> call=iUser.login(username,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body()!=null)
                        {
                            session.createUserLoginSession("Prof Session", Objects.requireNonNull(response.body()).getEmail());
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("cin",Integer.toString(response.body().getCin()));
                            editor.putString("lName",response.body().getLname());
                            editor.putString("fName",response.body().getFname());
                            editor.putString("username",response.body().getUsername());
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Connected", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        }else
                        {
                            Log.d("Error: ",response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Log.d("Erreur: ",t.getMessage());
                    }
                });
            }
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
