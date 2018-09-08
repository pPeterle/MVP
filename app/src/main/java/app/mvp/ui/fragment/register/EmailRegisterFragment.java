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
import app.mvp.helper.ValidatorHelper;

public class EmailRegisterFragment extends Fragment {
    public Bundle args;
    public String name, nickname;

    private TextInputEditText et_email;
    private TextInputLayout il_email;
    private ImageButton btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();

        if (args != null) {
            name = args.getString("name");
            nickname = args.getString("nickname");
        }
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
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            if (contentFieldsIsValid()) {
                btn_next.setEnabled(false);

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("nickname", nickname);
                bundle.putString("email", et_email.getText().toString());

                FragmentHelper.load(new PhoneRegisterFragment(), true, bundle, getActivity());
            }
        }
    };

    private boolean contentFieldsIsValid() {
        if (emailIsEmpty()) {
            il_email.setError(getString(R.string.empty_email));
            return false;
        }

        if (notIsEmail()) {
            il_email.setError(getString(R.string.invalid_email));
            return false;
        }
        return true;
    }

    private boolean emailIsEmpty() {
        return ValidatorHelper.isEmpty(et_email.getText().toString());
    }

    private boolean notIsEmail() {
        return !ValidatorHelper.isEmail(et_email.getText().toString());
    }

    private void cleanErrorMessageFields() {
        il_email.setError(null);
        il_email.setErrorEnabled(false);
    }
}
