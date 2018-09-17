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
import android.widget.TextView;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;

public class NicknameRegisterFragment extends Fragment implements NicknameRegisterContract.NicknameRegisterView {
    public Bundle args;
    public TextView tv_firstname;
    public String name;

    private TextInputEditText et_nickname;
    private TextInputLayout il_nickname;
    private ImageButton btn_next;

    private NicknameRegisterContract.NicknameRegisterPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (presenter == null) {
            presenter = new NicknameRegisterPresenter(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nickname_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (args != null) {
            tv_firstname = view.findViewById(R.id.tv_firstname);
            tv_firstname.setText(firstWord(args.getString("name")));
            name = args.getString("name");
        }

        et_nickname = view.findViewById(R.id.et_nickname);
        il_nickname = view.findViewById(R.id.il_nickname);

        et_nickname.setFocusable(true);
        et_nickname.requestFocus();

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(next);
    }

    public static String firstWord(String string) {
        return string.split(" ")[0];
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            presenter.callEmailRegister(et_nickname.getText().toString().trim());
        }
    };

    private void cleanErrorMessageFields() {
        il_nickname.setError(null);
        il_nickname.setErrorEnabled(false);
    }

    @Override
    public void nicknameIsEmpty() {
        il_nickname.setError(getString(R.string.empty_nickname));
    }

    @Override
    public void openEmailRegister(String nickname) {
        btn_next.setEnabled(false);

        //String nickname = et_nickname.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("nickname", nickname.substring(0,1).toUpperCase() + nickname.substring(1));

        FragmentHelper.load(new EmailRegisterFragment(), true, bundle, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
