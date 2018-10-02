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
import app.mvp.helper.PhoneMaskHelper;
import app.mvp.model.User;

public class PhoneRegisterView extends Fragment implements PhoneRegisterContract.PhoneRegisterView {
    private PhoneRegisterContract.PhoneRegisterPresenter presenter;

    private User user;
    private TextInputEditText et_phone;
    private TextInputLayout il_phone;
    private ImageButton btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User();
        user = registerUser();

        if (presenter == null) {
            presenter = new PhoneRegisterPresenter(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phone_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_phone = view.findViewById(R.id.et_phone);
        il_phone = view.findViewById(R.id.il_phone);

        et_phone.setFocusable(true);
        et_phone.requestFocus();

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);

        et_phone.addTextChangedListener(PhoneMaskHelper.insert(et_phone));
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            presenter.callPasswordRegister(et_phone.getText().toString());
        }
    };

    private User registerUser() {
        Bundle args = getArguments();

        if (args != null) {
            user.setName(args.getString("name"));
            user.setNickname(args.getString("nickname"));
            user.setEmail(args.getString("email"));
        }
        return user;
    }

    private void cleanErrorMessageFields() {
        il_phone.setError(null);
        il_phone.setErrorEnabled(false);
    }

    @Override
    public void phoneIsEmpty() {
        il_phone.setError(getString(R.string.empty_phone));
    }

    @Override
    public void notIsPhone() {
        il_phone.setError(getString(R.string.invalid_phone_length));
    }

    @Override
    public void openPasswordRegister(String phone) {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("name", user.getName());
        bundle.putString("nickname", user.getNickname());
        bundle.putString("email", user.getEmail());
        bundle.putString("phone", phone);

        FragmentHelper.load(new PasswordRegisterView(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
