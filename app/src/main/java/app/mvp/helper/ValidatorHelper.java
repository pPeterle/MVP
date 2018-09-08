package app.mvp.helper;

import android.text.TextUtils;

public class ValidatorHelper {
    public static boolean isEmail(String string) {
        return PatternHelper.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", string);
    }

    public static boolean isPassword(String string) {
        return PatternHelper.pattern("\\b[a-zA-Z0-9@#$%!*\\.\\-_]{3,}\\b", string); // ((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!]).{8,})
    }

    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public static boolean isNumeric(String string) {
        return TextUtils.isDigitsOnly(string);
    }
}
