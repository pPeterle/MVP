package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    // Não finalizado
    interface PasswordLoginModel {

        void gravar(User resp);

    }

    interface PasswordLoginView {

        void passwordIsEmpty();

        void notIsPassword();

        void onFailure(); // Quando ocorre um erro na requisição do Retrofit

        void errorLogin();

        void openDashboard(); // Abre o Dashboard do usuário logado

    }

    interface PasswordLoginPresenter {

        void callLoginProcess(User user); // Faz o login

        void onPause();

        void onDestroy();

    }

}
