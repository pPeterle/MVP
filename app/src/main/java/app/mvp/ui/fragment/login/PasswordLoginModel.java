package app.mvp.ui.fragment.login;

import android.support.annotation.NonNull;

import app.mvp.R;
import app.mvp.helper.ResponseHelper;
import app.mvp.model.User;
import app.mvp.model.server.NoConnectivityException;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginModel implements PasswordLoginContract.PasswordLoginModel {
    private PasswordLoginContract.PasswordLoginPresenter presenter;
    private Call<User> call;
    private LoginService loginService;

    PasswordLoginModel(PasswordLoginContract.PasswordLoginPresenter presenter){
        this.presenter = presenter;
        this.loginService = Config.getLoginService();
    }

    @Override
    public void request(User user) {
        call = loginService.login(user);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                final User resp = response.body();

                if (ResponseHelper.isValid(resp, response)) {
                    presenter.openDashboard(resp);
                } else {
                    presenter.error(R.string.error_login_response);
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
