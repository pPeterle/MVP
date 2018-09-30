package app.mvp.ui.fragment.login;

import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.session.Session;

public class PasswordLoginPresenter implements PasswordLoginContract.PasswordLoginPresenter {
    private PasswordLoginContract.PasswordLoginView view;
    private PasswordLoginContract.PasswordLoginModel model;

    private Session session;

    PasswordLoginPresenter(PasswordLoginContract.PasswordLoginView view, Session session) {
        this.view = view;
        this.session = session;

        model = new PasswordLoginModel(this);
    }

    @Override
    public void callLoginProcess(User user) {
        // Lógica
        if (contentFieldsIsValid(user.getPassword())) {
            // Faz a requisição com o Retrofit2 e pega os dados da API
            model.request(user);
        }
    }

    @Override
    public void errorRequest() {
        view.errorRequest();
    }

    @Override
    public void errorLogin() {
        view.errorLogin();
    }

    @Override
    public void openDashboard(User resp) {
        // Salva os dados retornados da API, na sessão (SharedPreferences)
        session.setLogin(resp);

        view.openDashboard();
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
        model.onPause();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
