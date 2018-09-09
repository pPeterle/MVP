package app.mvp.ui.activity;

public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginContract.LoginView view;

    LoginPresenter(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void onBackPressed(int count) {
        if (count == 0) {
            view.abreHome();
        } else {
            view.popBackStack();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
