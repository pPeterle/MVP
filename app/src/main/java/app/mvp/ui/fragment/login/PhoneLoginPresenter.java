package app.mvp.ui.fragment.login;

public class PhoneLoginPresenter implements PhoneLoginContract.PhoneLoginPresenter {
    private PhoneLoginContract.PhoneLoginView view;

    PhoneLoginPresenter(PhoneLoginContract.PhoneLoginView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        // TODO
    }

    @Override
    public void passwordFragment() {
        // ANTES PRECISO PEGAR TODA AQUELA LÓGICA DO FRAGMENT =/
        view.abrePasswordFragment();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
