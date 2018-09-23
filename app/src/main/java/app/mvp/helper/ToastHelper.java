package app.mvp.helper;

import android.app.Activity;
import android.widget.Toast;

import app.mvp.model.User;
import retrofit2.Response;

public class ToastHelper {
    public static void alert(String message, Activity activity) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidaResponse(User resp, Response<User> response) {
        return resp != null && response.isSuccessful() && response.body() != null;
    }
}
