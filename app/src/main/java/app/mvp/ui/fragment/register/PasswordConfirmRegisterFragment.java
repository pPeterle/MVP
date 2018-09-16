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
import app.mvp.helper.ValidatorHelper;
import app.mvp.ui.activity.terms.TermsActivity;

public class PasswordConfirmRegisterFragment extends Fragment {
    public Bundle args;
    public String name, nickname, email, phone, password;
    public Intent intent;
    public TextView tv_terms;
    public CheckBox checkBox;
    public ProgressBar progress;

    private TextInputEditText et_password_confirm;
    private TextInputLayout il_password_confirm;
    private ImageButton btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();

        if (args != null) {
            name = args.getString("name");
            nickname = args.getString("nickname");
            email = args.getString("email");
            phone = args.getString("phone");
            password = args.getString("password");
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
            cleanErrorMessageFields();

            if (contentFieldsIsValid()) {
                buttonNextEnabled(false);

                KeyboardToggleHelper.toggle(getActivity());
                et_password_confirm.setEnabled(false);
                tv_terms.setEnabled(false);
                checkBox.setEnabled(false);
                //progress.setVisibility(View.VISIBLE);

                String dados = "Nome completo: " + name + " Apelido: " + nickname + " E-mail: " + email + " Telefone: " + phone + " Senha: " + password;
                ToastHelper.alert(dados, getActivity());
            }
        }
    };

    private boolean contentFieldsIsValid() {
        if (passwordIsEmpty()) {
            il_password_confirm.setError(getString(R.string.empty_password_confirm));
            return false;
        }

        if (notIsSamePassword()) {
            il_password_confirm.setError(getString(R.string.invalid_password_confirm));
            return false;
        }

        if (notIsChecked()) {
            ToastHelper.alert(getResources().getString(R.string.error_accept), getActivity());
            return false;
        }
        return true;
    }

    private boolean passwordIsEmpty() {
        return ValidatorHelper.isEmpty(et_password_confirm.getText().toString());
    }

    private boolean notIsSamePassword() {
        return !et_password_confirm.getText().toString().equals(password);
    }

    private boolean notIsChecked() {
        return !checkBox.isChecked();
    }

    private void buttonNextEnabled(Boolean enabled) {
        btn_next.setEnabled(enabled);
    }

    private void cleanErrorMessageFields() {
        il_password_confirm.setError(null);
        il_password_confirm.setErrorEnabled(false);
    }
}
