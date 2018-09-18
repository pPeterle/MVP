package app.mvp.ui.fragment.register;

import android.support.annotation.NonNull;

import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordConfirmRegisterPresenter implements PasswordConfirmRegisterContract.PasswordConfirmRegisterPresenter {
    private PasswordConfirmRegisterContract.PasswordConfirmRegisterView view;
    private UserService userService;
    private Call<User> response;

    PasswordConfirmRegisterPresenter(PasswordConfirmRegisterContract.PasswordConfirmRegisterView view) {
        this.view = view;
        this.userService = Config.getUserService();
    }

    @Override
    public void callDashboard(User user) {

        if (contentFieldsIsValid(user)) {

            response = userService.register(user);
            response.enqueue(new Callback<User>() {

                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                    final User resp = response.body();

                    if (resp != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                view.openDashboard(resp);
                            }
                        }
                    } else {
                        view.errorRegister();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    view.onFailure();
                }
            });
        }
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
        if (response != null) {
            response.cancel();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
