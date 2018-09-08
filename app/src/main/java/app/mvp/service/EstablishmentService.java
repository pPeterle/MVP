package app.mvp.service;

import app.mvp.model.Establishment;
import app.mvp.model.server.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface EstablishmentService {
    @GET("establishment/{name}")
    Call<Establishment> search(@Path("name") String name);

    @GET("user/{uuid}/establishments")
    Call<Response> all(@Header("Authorization") String token, @Path("uuid") String uuid);
}
