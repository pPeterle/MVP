package app.mvp.ui.fragment.register;

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
import app.mvp.model.User;

public class PasswordRegisterView extends Fragment implements PasswordRegisterContract.PasswordRegisterView {
    private PasswordRegisterContract.PasswordRegisterPresenter presenter;

    private User user;
    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private ImageButton btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User();
        user = registerUser();
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

        if (presenter == null) {
            presenter = new PasswordRegisterPresenter(this);
        }
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            presenter.callPasswordConfirmRegister(et_password.getText().toString());
        }
    };

    private User registerUser() {
        Bundle args = getArguments();

        if (args != null) {
            user.setName(args.getString("name"));
            user.setNickname(args.getString("nickname"));
            user.setEmail(args.getString("email"));
            user.setPhone(args.getString("phone"));
        }
        return user;
    }

    private void cleanErrorMessageFields() {
        il_password.setError(null);
        il_password.setErrorEnabled(false);
    }

    @Override
    public void error(int error) {
        il_password.setError(getString(error));
    }

    @Override
    public void openPasswordConfirmRegister(String password) {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("name", user.getName());
        bundle.putString("nickname", user.getNickname());
        bundle.putString("email", user.getEmail());
        bundle.putString("phone", user.getPhone());
        bundle.putString("password", password);

        FragmentHelper.load(new PasswordConfirmRegisterView(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
