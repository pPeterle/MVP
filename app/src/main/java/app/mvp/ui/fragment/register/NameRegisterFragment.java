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

public class NameRegisterFragment extends Fragment implements NameRegisterContract.NameRegisterView {
    private TextInputEditText et_name;
    private TextInputLayout il_name;
    private ImageButton btn_next;

    private NameRegisterContract.NameRegisterPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (presenter == null) {
            presenter = new NameRegisterPresenter(this);
        }
    }

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

            presenter.callNicknameRegister(et_name.getText().toString().trim());
        }
    };

    private void cleanErrorMessageFields() {
        il_name.setError(null);
        il_name.setErrorEnabled(false);
    }

    @Override
    public void nameIsEmpty() {
        il_name.setError(getString(R.string.empty_name));
    }

    @Override
    public void notIsFullname() {
        il_name.setError(getString(R.string.empty_name));
    }

    @Override
    public void openNicknameRegister(String name) {
        btn_next.setEnabled(false);

        Bundle bundle = new Bundle();
        bundle.putString("name", name.substring(0,1).toUpperCase() + name.substring(1));

        FragmentHelper.load(new NicknameRegisterFragment(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
