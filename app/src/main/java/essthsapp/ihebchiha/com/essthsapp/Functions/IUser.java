package essthsapp.ihebchiha.com.essthsapp.Functions;

import essthsapp.ihebchiha.com.essthsapp.Models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IUser {
    @GET("/user/login/")
    Call<User> login(@Query("username")String user,@Query("password")String password);

}
