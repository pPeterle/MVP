package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    interface PasswordLoginModel {

        // Faz a requisição
        void request(User user);

        // Cancela a requisição
        void onPause();

    }

    interface PasswordLoginView {

        void passwordIsEmpty();

        void notIsPassword();

        // Pega o erro que o Presenter recebeu do Model
        void errorRequest();

        // Pega o erro que o Presenter recebeu do Model
        void errorLogin();

        // Abre a Activity
        void openDashboard();

    }

    interface PasswordLoginPresenter {

        // Pede para o Model fazer a requisição
        void callLoginProcess(User user);

        // Pega o erro que recebeu do Model
        void errorRequest();

        // Pega o erro que recebeu do Model
        void errorLogin();

        // Salva os dados retornados do Model E manda a View abrir a Activity
        void openDashboard(User resp);

        // O onPause da View manda o Presenter pedir para o Model que cancele a requisição
        void onPause();

        void onDestroy();

    }

}
