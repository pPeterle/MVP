package app.mvp.ui.activity.login;

public interface LoginContract {

    interface LoginView {

        void initView();

        void openHome();

        void popBackStack();
    }

    interface LoginPresenter {

        void onBackPressed(int count);

        void onDestroy();

    }
}
