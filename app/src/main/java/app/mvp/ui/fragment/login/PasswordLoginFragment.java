package app.mvp.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import app.mvp.R;
import app.mvp.helper.KeyboardToggleHelper;
import app.mvp.helper.ToastHelper;
import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import app.mvp.session.Session;
import app.mvp.ui.activity.SplashActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginFragment extends Fragment implements PasswordLoginContract.PasswordLoginView {
    public Handler handler;
    public Intent intent;
    public Session session;
    public ProgressBar progress;

    private User user;
    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private ImageButton btn_next;

    private PasswordLoginContract.PasswordLoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        session = new Session(getActivity());
        user = new User();

        if (presenter == null) {
            presenter = new PasswordLoginPresenter(this);
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
            cleanErrorMessageFields();

            buttonNextEnabled(false);

            // Esconde o teclado
            KeyboardToggleHelper.toggle(getActivity());

            et_password.setEnabled(false);

            user = loginUser();
            presenter.callLoginProcess(user);
        }
    };

    private User loginUser() {
        Bundle args = getArguments();

        if (args != null) {
            String data = args.getString("phone");
            user.setPhone(data);
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
    public void errorProcess() {
        buttonNextEnabled(true);

        // Mostra o teclado
        KeyboardToggleHelper.toggle(getActivity());

        et_password.setEnabled(true);
        progress.setVisibility(View.GONE);

        ToastHelper.alert(getResources().getString(R.string.response_error), getActivity());
    }

    @Override
    public void openDashboard(User resp) {
        progress.setVisibility(View.GONE);

        // Grava os dados retornados do Presenter, na sessão
        session.setLogin(resp);

        intent = new Intent(getActivity(), SplashActivity.class);

        if (getActivity() != null) {
            getActivity().startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
