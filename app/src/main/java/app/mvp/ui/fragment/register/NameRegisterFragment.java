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

public class NameRegisterFragment extends Fragment {
    private TextInputEditText et_name;
    private TextInputLayout il_name;
    private ImageButton btn_next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_name = view.findViewById(R.id.et_name);
        il_name = view.findViewById(R.id.il_name);

        et_name.setFocusable(true);
        et_name.requestFocus();

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            if (contentFieldsIsValid()) {
                btn_next.setEnabled(false);

                String name = et_name.getText().toString().trim();

                Bundle bundle = new Bundle();
                bundle.putString("name", name.substring(0,1).toUpperCase() + name.substring(1));

                FragmentHelper.load(new NicknameRegisterFragment(), true, bundle, getActivity());
            }
        }
    };

    private boolean contentFieldsIsValid() {
        if (nameIsEmpty()) {
            il_name.setError(getString(R.string.empty_name));
            return false;
        }

        if (notIsFullname(et_name.getText().toString().trim())) {
            il_name.setError(getString(R.string.empty_name));
            return false;
        }
        return true;
    }

    private boolean nameIsEmpty() {
        return ValidatorHelper.isEmpty(et_name.getText().toString().trim());
    }

    private boolean notIsFullname(String string) {
        return !string.contains(" ");
    }

    private void cleanErrorMessageFields() {
        il_name.setError(null);
        il_name.setErrorEnabled(false);
    }
}
