package app.mvp.helper;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class KeyboardToggleHelper {
    public static void toggle(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (imm.isActive()){
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
            } else {
                imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY); // show
            }
        }
    }
}
