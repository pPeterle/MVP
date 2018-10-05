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

public class EmailRegisterView extends Fragment implements EmailRegisterContract.EmailRegisterView {
    private EmailRegisterContract.EmailRegisterPresenter presenter;

    private User user;
    private TextInputEditText et_email;
    private TextInputLayout il_email;
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
        return inflater.inflate(R.layout.fragment_email_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_email = view.findViewById(R.id.et_email);
        il_email = view.findViewById(R.id.il_email);

        et_email.setFocusable(true);
        et_email.requestFocus();

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);

        if (presenter == null) {
            presenter = new EmailRegisterPresenter(this);
        }
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            presenter.callPhoneRegister(et_email.getText().toString());
        }
    };

    private User registerUser() {
        Bundle args = getArguments();

        if (args != null) {
            user.setName(args.getString("name"));
            user.setNickname(args.getString("nickname"));
        }
        return user;
    }

    private void cleanErrorMessageFields() {
        il_email.setError(null);
        il_email.setErrorEnabled(false);
    }

    @Override
    public void error(int error) {
        il_email.setError(getString(error));
    }

    @Override
    public void openPhoneRegister(String email) {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("name", user.getName());
        bundle.putString("nickname", user.getNickname());
        bundle.putString("email", email);

        FragmentHelper.load(new PhoneRegisterView(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
