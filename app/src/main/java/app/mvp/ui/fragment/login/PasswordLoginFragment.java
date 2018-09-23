package app.mvp.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import app.mvp.App;
import app.mvp.R;
import app.mvp.helper.KeyboardToggleHelper;
import app.mvp.helper.ToastHelper;
import app.mvp.model.User;
import app.mvp.session.Session;
import app.mvp.ui.activity.splash.SplashActivity;

public class PasswordLoginFragment extends Fragment implements PasswordLoginContract.PasswordLoginView {
    public Intent intent;
    public ProgressBar progress;

    private ImageButton btn_next;
    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private User user;

    private PasswordLoginContract.PasswordLoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Acho que preciso melhorar isso
        user = new User();

        Session session = ((App) getActivity().getApplication()).getSession();

        if (presenter == null) {
            presenter = new PasswordLoginPresenter(this, session);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_password = view.findViewById(R.id.et_password);
        il_password = view.findViewById(R.id.il_password);

        et_password.setFocusable(true);
        et_password.requestFocus();

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);

        progress = view.findViewById(R.id.progress);
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Esconde o teclado quando clico em logar
            KeyboardToggleHelper.toggle(getActivity());

            cleanErrorMessageFields();
            buttonNextEnabled(false);
            et_password.setEnabled(false);
            progress.setVisibility(View.VISIBLE);

            user = loginUser();
            presenter.callLoginProcess(user);
        }
    };

    private User loginUser() {
        Bundle args = getArguments();

        if (args != null) {
            user.setPhone(args.getString("phone"));
            user.setPassword(et_password.getText().toString());
        }
        return user;
    }

    private void cleanErrorMessageFields() {
        il_password.setError(null);
        il_password.setErrorEnabled(false);
    }

    private void buttonNextEnabled(Boolean enabled) {
        btn_next.setEnabled(enabled);
    }

    @Override
    public void passwordIsEmpty() {
        il_password.setError(getString(R.string.empty_password));
    }

    @Override
    public void notIsPassword() {
        il_password.setError(getString(R.string.invalid_password));
    }

    @Override
    public void onFailure() {
        // Mostra o teclado quando ocorre um erro na requisição do Retrofit
        KeyboardToggleHelper.toggle(getActivity());

        buttonNextEnabled(true);
        et_password.setEnabled(true);
        progress.setVisibility(View.GONE);

        ToastHelper.alert(getResources().getString(R.string.response_error), getActivity());
    }

    @Override
    public void errorLogin() {
        ToastHelper.alert("Telefone ou senha, incorreto", getActivity());
    }

    @Override
    public void openDashboard() {
        progress.setVisibility(View.GONE);

        intent = new Intent(getActivity(), SplashActivity.class);

        if (getActivity() != null) {
            getActivity().startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, 0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
