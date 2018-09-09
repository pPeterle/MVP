package app.mvp.ui.activity;

public interface LoginContract {

    interface LoginView {

        void abreHome();

        void popBackStack();
    }

    interface LoginPresenter {

        void onBackPressed(int count);

        void onDestroy();

    }
}
