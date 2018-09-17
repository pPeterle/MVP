package app.mvp.ui.fragment.register;

import android.content.Context;
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

import app.mvp.R;
import app.mvp.helper.FragmentHelper;

public class PasswordRegisterFragment extends Fragment implements PasswordRegisterContract.PasswordRegisterView {
    public Bundle args;
    public String name, nickname, email, phone;

    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private ImageButton btn_next;

    private PasswordRegisterContract.PasswordRegisterPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (presenter == null) {
            presenter = new PasswordRegisterPresenter(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();

        if (args != null) {
            name = args.getString("name");
            nickname = args.getString("nickname");
            email = args.getString("email");
            phone = args.getString("phone");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_register, container, false);
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
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            presenter.callPasswordConfirmRegister(et_password.getText().toString());
        }
    };

    private void cleanErrorMessageFields() {
        il_password.setError(null);
        il_password.setErrorEnabled(false);
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
    public void openPasswordConfirmRegister(String password) {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("nickname", nickname);
        bundle.putString("email", email);
        bundle.putString("phone", phone);
        bundle.putString("password", password);

        FragmentHelper.load(new PasswordConfirmRegisterFragment(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
