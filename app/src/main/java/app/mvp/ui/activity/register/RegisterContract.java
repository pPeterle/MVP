package app.mvp.ui.activity.register;

public interface RegisterContract {

    interface RegisterView {

        void initView();

        void openHome();

        void popBackStack();

    }

    interface RegisterPresenter {

        void onBackPressed(int count);

        void onDestroy();

    }

}
