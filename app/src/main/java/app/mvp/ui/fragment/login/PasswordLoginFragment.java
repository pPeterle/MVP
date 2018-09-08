package app.mvp.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import app.mvp.R;
import app.mvp.helper.KeyboardToggleHelper;
import app.mvp.helper.ToastHelper;
import app.mvp.helper.ValidatorHelper;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.LoginService;
import app.mvp.session.Session;
import app.mvp.ui.activity.SplashActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class PasswordLoginFragment extends Fragment {
    public Handler handler;
    public Intent intent;
    public LoginService loginService;
    public Session session;
    public ProgressBar progress;

    private User user;
    private TextInputEditText et_password;
    private TextInputLayout il_password;
    private ImageButton btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        loginService = Config.getLoginService();
        session = new Session(getActivity());
        user = new User();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_password, container, false);
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

        progress = view.findViewById(R.id.progress);
    }

    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cleanErrorMessageFields();

            if (contentFieldsIsValid()) {
                buttonNextEnabled(false);

                KeyboardToggleHelper.toggle(getActivity());
                et_password.setEnabled(false);
                progress.setVisibility(View.VISIBLE);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);

                        session.setLogin(user);

                        intent = new Intent(getActivity(), SplashActivity.class);

                        if (getActivity() != null) {
                            getActivity().startActivity(intent);
                            getActivity().finish();
                            getActivity().overridePendingTransition(R.anim.fade_in, 0);
                        }
                    }
                }, 3000);

                /*user = loginUser();
                loginProcess(user);*/
            }
        }
    };

    private boolean contentFieldsIsValid() {
        if (passwordIsEmpty()) {
            il_password.setError(getString(R.string.empty_password));
            return false;
        }

        if (notIsPassword()) {
            il_password.setError(getString(R.string.invalid_password));
            return false;
        }
        return true;
    }

    private boolean passwordIsEmpty() {
        return ValidatorHelper.isEmpty(et_password.getText().toString());
    }

    private boolean notIsPassword() {
        return !ValidatorHelper.isPassword(et_password.getText().toString());
    }

    private void buttonNextEnabled(Boolean enabled) {
        btn_next.setEnabled(enabled);
    }

    private User loginUser() {
        Bundle args = getArguments();

        if (args != null) {
            String data = args.getString("phone");
            user.setPhone(data);
            user.setPassword(et_password.getText().toString());
        }
        return user;
    }

    private boolean isNumeric(String data) {
        return ValidatorHelper.isNumeric(data);
    }

    private void cleanErrorMessageFields() {
        il_password.setError(null);
        il_password.setErrorEnabled(false);
    }

    public void loginProcess(User user) {
        Call<User> response = loginService.login(user);

        response.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                final User resp = response.body();

                if (resp != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            System.out.println("ResponseBody: " + resp);

                            /*session.setLogin(resp);

                            intent = new Intent(getActivity(), SplashActivity.class);

                            if (getActivity() != null) {
                                getActivity().startActivity(intent);
                                getActivity().finish();
                                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }*/
                        }
                    }
                }
                buttonNextEnabled(true);

                KeyboardToggleHelper.toggle(getActivity());
                et_password.setEnabled(true);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                buttonNextEnabled(true);

                KeyboardToggleHelper.toggle(getActivity());
                et_password.setEnabled(true);
                progress.setVisibility(View.GONE);
                ToastHelper.alert(getResources().getString(R.string.response_error), getActivity());
            }
        });
    }
}
