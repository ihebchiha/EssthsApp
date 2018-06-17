package essthsapp.ihebchiha.com.essthsapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import essthsapp.ihebchiha.com.essthsapp.Config.ConfigRetrofit;
import essthsapp.ihebchiha.com.essthsapp.Functions.IUser;
import essthsapp.ihebchiha.com.essthsapp.Models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends Activity {

    EditText anctxt,newtxt,conftxt;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        anctxt=findViewById(R.id.ancpasstxt);
        newtxt=findViewById(R.id.newpasstxt);
        conftxt=findViewById(R.id.confpasstxt);
        change=findViewById(R.id.changebtn);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String cin=preferences.getString("cin","0");
        Log.d("Cin= ",cin);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anc=anctxt.getText().toString();
                String ntxt=newtxt.getText().toString();
                String conf=conftxt.getText().toString();

                int c=Integer.parseInt(cin);
                if (anc.equals("")||ntxt.equals("")||conf.equals(""))
                {
                    Toast.makeText(ProfileEditActivity.this, "Veuillez remplir les champs SVP", Toast.LENGTH_SHORT).show();
                }else if (!ntxt.equals(conf))
                {
                    Toast.makeText(ProfileEditActivity.this,"Vérifiez si vous avez écrit la confirmation correctement",Toast.LENGTH_LONG).show();
                }else
                {
                    IUser iUser= ConfigRetrofit.retrofit.create(IUser.class);
                    Call<User> call=iUser.updateinfo(ntxt,c);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                            Toast.makeText(ProfileEditActivity.this, "Mot de passe est mis à jour", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(ProfileEditActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                }


            }
        });

    }
}
