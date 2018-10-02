package app.mvp.ui.fragment.register;

import android.support.annotation.NonNull;

import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.UserService;
import app.mvp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordConfirmRegisterPresenter implements PasswordConfirmRegisterContract.PasswordConfirmRegisterPresenter {
    private PasswordConfirmRegisterContract.PasswordConfirmRegisterView view;
    private PasswordConfirmRegisterContract.PasswordConfirmRegisterModel model;

    private Session session;

    PasswordConfirmRegisterPresenter(PasswordConfirmRegisterContract.PasswordConfirmRegisterView view, Session session) {
        this.view = view;
        this.session = session;

        model = new PasswordConfirmRegisterModel(this);
    }

    @Override
    public void callDashboard(User user) {
        if (contentFieldsIsValid(user)) {
            model.request(user);
        }
    }

    @Override
    public void errorRequest() {
        view.errorRequest();
    }

    @Override
    public void errorRegister() {
        view.errorRegister();
    }

    @Override
    public void openDashboard(User resp) {
        // Salva os dados retornados da API, na sessão (SharedPreferences)
        session.setLogin(resp);

        view.openDashboard();
    }

    private boolean contentFieldsIsValid(User user) {
        if (passwordIsEmpty(user.getPasswordConfirm())) {
            view.passwordIsEmpty();
            return false;
        }

        if (notIsSamePassword(user.getPassword(), user.getPasswordConfirm())) {
            view.notIsSamePassword();
            return false;
        }

        if (!user.getCheckBox()) {
            view.notIsChecked();
            return false;
        }
        return true;
    }

    private boolean passwordIsEmpty(String passwordConfirm) {
        return ValidatorHelper.isEmpty(passwordConfirm);
    }

    private boolean notIsSamePassword(String password, String passwordConfirm) {
        return !passwordConfirm.equals(password);
    }

    @Override
    public void onPause() {
        model.onPause();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
