package app.mvp.ui.fragment.register;

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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.mvp.R;
import app.mvp.helper.KeyboardToggleHelper;
import app.mvp.helper.ToastHelper;
import app.mvp.model.User;
import app.mvp.session.Session;
import app.mvp.ui.activity.splash.SplashActivity;
import app.mvp.ui.activity.terms.TermsActivity;

public class PasswordConfirmRegisterFragment extends Fragment implements PasswordConfirmRegisterContract.PasswordConfirmRegisterView {
    public Intent intent;
    public Session session;
    public TextView tv_terms;
    public CheckBox checkBox;
    public ProgressBar progress;

    private User user;
    private TextInputEditText et_password_confirm;
    private TextInputLayout il_password_confirm;
    private ImageButton btn_next;

    private PasswordConfirmRegisterContract.PasswordConfirmRegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(getActivity());
        user = new User();

        if (presenter == null) {
            presenter = new PasswordConfirmRegisterPresenter(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_confirm_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_password_confirm = view.findViewById(R.id.et_password_confirm);
        il_password_confirm = view.findViewById(R.id.il_password_confirm);

        et_password_confirm.setFocusable(true);
        et_password_confirm.requestFocus();

        tv_terms = view.findViewById(R.id.tv_terms);
        tv_terms.setOnClickListener(terms);

        checkBox = view.findViewById(R.id.checkBox);

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);

        progress = view.findViewById(R.id.progress);
    }

    private View.OnClickListener terms = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            intent = new Intent(getActivity(), TermsActivity.class);

            if (getActivity() != null) {
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.stable);
            }
        }
    };

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Esconde o teclado
            KeyboardToggleHelper.toggle(getActivity());

            cleanErrorMessageFields();
            buttonNextEnabled(false);
            et_password_confirm.setEnabled(false);
            progress.setVisibility(View.VISIBLE);

            user = registerUser();
            presenter.callDashboard(user);
        }
    };

    private User registerUser() {
        Bundle args = getArguments();

        if (args != null) {
            user.setName(args.getString("name"));
            user.setNickname(args.getString("nickname"));
            user.setEmail(args.getString("email"));
            user.setPhone(args.getString("phone"));
            user.setPassword(args.getString("password"));
            user.setPasswordConfirm(et_password_confirm.getText().toString());
            user.setCheckBox(checkBox.isChecked());
        }
        return user;
    }

    private void cleanErrorMessageFields() {
        il_password_confirm.setError(null);
        il_password_confirm.setErrorEnabled(false);
    }

    private void buttonNextEnabled(Boolean enabled) {
        btn_next.setEnabled(enabled);
    }

    @Override
    public void passwordIsEmpty() {
        il_password_confirm.setError(getString(R.string.empty_password_confirm));
    }

    @Override
    public void notIsSamePassword() {
        il_password_confirm.setError(getString(R.string.invalid_password_confirm));
    }

    @Override
    public void notIsChecked() {
        ToastHelper.alert(getResources().getString(R.string.error_accept), getActivity());
    }

    @Override
    public void onFailure() {
        // Mostra o teclado
        KeyboardToggleHelper.toggle(getActivity());

        buttonNextEnabled(true);
        et_password_confirm.setEnabled(true);
        progress.setVisibility(View.GONE);

        ToastHelper.alert(getResources().getString(R.string.response_error), getActivity());
    }

    @Override
    public void errorRegister() {
        ToastHelper.alert("Erro ao se cadastrar, tente novamente mais tarde", getActivity());
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
