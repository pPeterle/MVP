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
import app.mvp.helper.ValidatorHelper;

public class PhoneLoginFragment extends Fragment implements PhoneLoginContract.PhoneLoginView {
    private TextInputEditText et_phone;
    private TextInputLayout il_phone;
    private ImageButton btn_next;

    private PhoneLoginContract.PhoneLoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (presenter == null) {
            presenter = new PhoneLoginPresenter(this);
        }
    }

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
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cleanErrorMessageFields();

            presenter.passwordFragment();

            // POR TODA ESSA LÓGICA NO PRESENTER

            /*if (contentFieldsIsValid()) {
                buttonNextEnabled(false);

                Bundle bundle = new Bundle();
                bundle.putString("phone", et_phone.getText().toString());

                FragmentHelper.load(new PasswordLoginFragment(), true, bundle, getActivity());
            }*/
        }
    };

    @Override
    public void abrePasswordFragment() {
        // Chama o fragment
    }

    private boolean contentFieldsIsValid() {
        if (phoneIsEmpty()) {
            il_phone.setError(getString(R.string.empty_phone));
            return false;
        }

        if (isNumeric()) {
            if (et_phone.getText().toString().length() < 10) {
                il_phone.setError(getString(R.string.invalid_phone_length));
                return false;
            }
            return true;
        }
        return true;
    }

    private boolean phoneIsEmpty() {
        return ValidatorHelper.isEmpty(et_phone.getText().toString());
    }

    private boolean isNumeric() {
        return ValidatorHelper.isNumeric(et_phone.getText().toString());
    }

    private void buttonNextEnabled(Boolean enabled) {
        btn_next.setEnabled(enabled);
    }

    private void cleanErrorMessageFields() {
        il_phone.setError(null);
        il_phone.setErrorEnabled(false);
    }
}
