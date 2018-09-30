package app.mvp.ui.fragment.login;

import android.support.annotation.NonNull;

import app.mvp.helper.ResponseHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginModel implements PasswordLoginContract.PasswordLoginModel {
    private PasswordLoginContract.PasswordLoginPresenter presenter;
    private Call<User> response;
    private LoginService loginService;

    PasswordLoginModel(PasswordLoginContract.PasswordLoginPresenter presenter){
        this.presenter = presenter;
        this.loginService = Config.getLoginService();
    }

    @Override
    public void request(User user) {
        response = loginService.login(user);
        response.enqueue(new Callback<User>() {

            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                final User resp = response.body();

                if (ResponseHelper.isValid(resp, response)) {
                    presenter.openDashboard(resp);
                } else {
                    presenter.errorLogin();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                if (!call.isCanceled()) {
                    presenter.errorRequest();
                }
            }
        });
    }

    @Override
    public void onPause() {
        if (response != null) {
            response.cancel();
        }
    }
}
