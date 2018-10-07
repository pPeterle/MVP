package app.mvp.ui.fragment.login;

import android.content.Context;
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
import app.mvp.ui.activity.login.LoginView;
import app.mvp.ui.activity.splash.SplashView;

public class PasswordLoginView extends Fragment implements PasswordLoginContract.PasswordLoginView {
    private PasswordLoginContract.PasswordLoginPresenter presenter;

    public Intent intent;
    public ProgressBar progress;

    private ImageButton btn_next;
    private Session session;
    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private User user;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // USAR DAGGER


        LoginView activity = (LoginView) context;
        session = ((App) activity.getApplication()).getSession();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tantar não usar
        user = new User();
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

        if (presenter == null) {
            presenter = new PasswordLoginPresenter(this, session);
        }
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
            presenter.loginProcess(user);
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
    public void error(int error) {
        // Mostra o teclado quando ocorre um erro na requisição do Retrofit
        KeyboardToggleHelper.toggle(getActivity());

        buttonNextEnabled(true);
        et_password.setEnabled(true);
        progress.setVisibility(View.GONE);

        ToastHelper.alert(getString(error), getActivity());
    }

    @Override
    public void openDashboard() {
        progress.setVisibility(View.GONE);

        intent = new Intent(getActivity(), SplashView.class);

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
