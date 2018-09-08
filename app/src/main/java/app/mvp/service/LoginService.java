package app.mvp.service;

import app.mvp.model.server.Response;
import app.mvp.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<User> login(@Body User user);

    @POST("login/password")
    Call<Response> forgotPassword(@Field("email") String email);
}
