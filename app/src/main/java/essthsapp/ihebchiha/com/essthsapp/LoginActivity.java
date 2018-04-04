package essthsapp.ihebchiha.com.essthsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import essthsapp.ihebchiha.com.essthsapp.Config.ConfigRetrofit;
import essthsapp.ihebchiha.com.essthsapp.Functions.IUser;
import essthsapp.ihebchiha.com.essthsapp.Modules.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    EditText user,passw;
    Button connect;
    AnimationDrawable animationDrawable;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.usernametxt);
        passw=findViewById(R.id.passwordtxt);
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
                            Toast.makeText(LoginActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this ,MenuActivity.class));
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
}
