package app.mvp.ui.fragment.login;

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

public class PhoneLoginView extends Fragment implements PhoneLoginContract.PhoneLoginView {
    private PhoneLoginContract.PhoneLoginPresenter presenter;

    private TextInputEditText et_phone;
    private TextInputLayout il_phone;
    private ImageButton btn_next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_phone, container, false);
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

        if (presenter == null) {
            presenter = new PhoneLoginPresenter(this);
        }
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cleanErrorMessageFields();

            presenter.callPasswordLogin(et_phone.getText().toString());
        }
    };

    private void cleanErrorMessageFields() {
        il_phone.setError(null);
        il_phone.setErrorEnabled(false);
    }

    @Override
    public void error(int error) {
        il_phone.setError(getString(error));
    }

    @Override
    public void openPasswordLogin() {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("phone", et_phone.getText().toString());

        FragmentHelper.load(new PasswordLoginView(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
