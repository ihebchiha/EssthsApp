package essthsapp.ihebchiha.com.essthsapp.Functions;

import essthsapp.ihebchiha.com.essthsapp.Modules.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUser {
    @GET("/user/login/")
    Call<User> login(@Query("username")String user,@Query("cin")String cin);

    @GET("/users/addPhoto/")
    Call<User> addPhoto(@Query("imglink")String imglink,@Query("cin")String cin);

    @GET("/users/userInfo/")
    Call<User> getFoto(@Query("cin") String cin);
}
