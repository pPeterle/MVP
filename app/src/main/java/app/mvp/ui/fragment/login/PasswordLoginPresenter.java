package app.mvp.ui.fragment.login;

import java.io.IOException;

import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import app.mvp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginPresenter implements PasswordLoginContract.PasswordLoginPresenter {
    private PasswordLoginContract.PasswordLoginView view;
    private LoginService loginService;
    private Call<User> response;

    private Session session;

    PasswordLoginPresenter(PasswordLoginContract.PasswordLoginView view) {
        this.view = view;
        this.loginService = Config.getLoginService();

        //session = new Session();
    }

    @Override
    public void callLoginProcess(User user) {
        if (contentFieldsIsValid(user.getPassword())) {

            response = loginService.login(user);
            response.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                    final User resp = response.body();

                    if (resp != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                // Grava os dados retornados do Presenter, na sessão
                                session.setLogin(resp);
                                view.openDashboard();
                            }
                        }
                    } else {
                        view.errorLogin();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    if (call instanceof IOException || t instanceof IOException) throw new NullArgumentException();

                    if (!call.isCanceled()) {
                        view.onFailure();
                    }
                }
            });
        }
    }

    class NullArgumentException extends IllegalArgumentException {

        public NullArgumentException() {
            super("arg cannot be null");
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
    public void onPause() {
        if (response != null) {
            response.cancel();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
