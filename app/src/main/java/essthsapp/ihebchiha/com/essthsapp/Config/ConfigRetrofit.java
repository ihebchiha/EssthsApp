package essthsapp.ihebchiha.com.essthsapp.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static Gson gson=new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.10:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
