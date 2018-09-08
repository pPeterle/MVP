package app.mvp.model.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.mvp.model.Establishment;
import app.mvp.model.User;

public class Response {
    private User user;
    private List<Establishment> establishments;

    public User getUser() {
        return user;
    }

    @SerializedName("establishments")
    public List<Establishment> getEstablishments() {
        return establishments;
    }
}
