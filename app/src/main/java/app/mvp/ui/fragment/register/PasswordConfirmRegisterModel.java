package app.mvp.ui.fragment.register;

import android.support.annotation.NonNull;

import app.mvp.R;
import app.mvp.helper.ResponseHelper;
import app.mvp.model.User;
import app.mvp.model.server.NoConnectivityException;
import app.mvp.retrofit.Config;
import app.mvp.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordConfirmRegisterModel implements PasswordConfirmRegisterContract.PasswordConfirmRegisterModel {
    private PasswordConfirmRegisterContract.PasswordConfirmRegisterPresenter presenter;
    private Call<User> call;
    private UserService userService;

    PasswordConfirmRegisterModel(PasswordConfirmRegisterContract.PasswordConfirmRegisterPresenter presenter){
        this.presenter = presenter;
        this.userService = Config.getUserService();
    }

    @Override
    public void request(User user) {
        call = userService.register(user);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                final User resp = response.body();

                if (ResponseHelper.isValid(resp, response)) {
                    presenter.openDashboard(resp);
                } else {
                    presenter.error(R.string.error_register_response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    presenter.error(R.string.error_request);
                }

                if (!call.isCanceled()) {
                    presenter.error(R.string.error_request);
                }
            }
        });
    }

    @Override
    public void onPause() {
        if (call != null) {
            call.cancel();
        }
    }
}
