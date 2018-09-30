package app.mvp.helper;

import app.mvp.model.User;
import retrofit2.Response;

public class ResponseHelper {
    public static boolean isValid(User resp, Response<User> response) {
        return resp != null && response.isSuccessful() && response.body() != null;
    }
}
