package app.mvp.ui.fragment.login;

import app.mvp.helper.ResponseHelper;
import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import app.mvp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginPresenter implements PasswordLoginContract.PasswordLoginPresenter {

    // View
    private PasswordLoginContract.PasswordLoginView view;

    // Model ( EM CONSTRUÇÃO )
    private PasswordLoginContract.PasswordLoginModel model;

    private Call<User> response;
    private LoginService loginService;

    private Session session;

    PasswordLoginPresenter(PasswordLoginContract.PasswordLoginView view, Session session) {
        this.view = view;
        this.loginService = Config.getLoginService();
        this.session = session;
    }

    @Override
    public void callLoginProcess(User user) {
        if (contentFieldsIsValid(user.getPassword())) {

            response = loginService.login(user);
            response.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                    final User resp = response.body();

                    if (ResponseHelper.isValid(resp, response)) {
                        model.gravar(resp); // session.setLogin(resp);

                        view.openDashboard();
                    } else {
                        view.errorLogin();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    if (!call.isCanceled()) {
                        view.onFailure();
                    }
                }
            });
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
