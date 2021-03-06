package app.mvp.service;

import app.mvp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("register")
    Call<User> register(@Body User user);
}
