package app.mvp.ui.fragment.login;

import android.support.annotation.NonNull;

import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginPresenter implements PasswordLoginContract.PasswordLoginPresenter {
    private PasswordLoginContract.PasswordLoginView view;
    private LoginService loginService;
    private Call<User> response;

    PasswordLoginPresenter(PasswordLoginContract.PasswordLoginView view) {
        this.view = view;
        this.loginService = Config.getLoginService();
    }

    @Override
    public void callLoginProcess(User user) {

        if (contentFieldsIsValid(user.getPassword())) {

            response = loginService.login(user);
            response.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                    final User resp = response.body();

                    if (resp != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                view.openDashboard(resp);
                            }
                        }
                    } else {
                        view.errorLogin();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    view.onFailure();
                }
            });
        }
    }

    @Override
    public void onPause() {
        if (response != null) {
            response.cancel();
        }
    }

    private boolean contentFieldsIsValid(String password) {
        if (passwordIsEmpty(password)) {
            view.passwordIsEmpty();
            return false;
        }

        if (notIsPassword(password)) {
            view.notIsPassword();
            return false;
        }
        return true;
    }

    private boolean passwordIsEmpty(String password) {
        return ValidatorHelper.isEmpty(password);
    }

    private boolean notIsPassword(String password) {
        return !ValidatorHelper.isPassword(password);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
