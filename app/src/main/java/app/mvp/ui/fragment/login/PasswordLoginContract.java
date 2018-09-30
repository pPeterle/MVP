package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    interface PasswordLoginModel {

        // Responsável por fazer a requisição com Retrofit2
        void request(User user);

        // Quando a requisição é cancelada
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

        // O Presenter pede para o Model, fazer a requisição
        void callLoginProcess(User user);

        // Pega o erro que o Presenter recebeu do Model
        void errorRequest();

        // Pega o erro que o Presenter recebeu do Model
        void errorLogin();

        // Salva os dados retornados do Model E manda a View abrir a Activity
        void openDashboard(User resp);

        // O onPause da View, pede para o Presenter cancelar a requisição que está sendo feita pelo Model
        void onPause();

        void onDestroy();

    }

}
