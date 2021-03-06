package app.mvp.helper;

import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;

public abstract class PhoneMaskHelper {
    private static final String mask8 = "####-####";
    private static final String mask9 = "#####-####";
    private static final String mask10 = "(##) ####-####";
    private static final String mask11 = "(##) #####-####";

    public static TextWatcher insert(final TextInputEditText et) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String str = PhoneMaskHelper.unmask(cs.toString());
                String mask;
                String defaultMask = getDefaultMask(str);

                switch (str.length()) {
                    case 9:
                        mask = mask9;
                        break;
                    case 10:
                        mask = mask10;
                        break;
                    case 11:
                        mask = mask11;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int i = 0;

                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                et.setText(mascara);
                et.setSelection(mascara.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }

    private static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    private static String getDefaultMask(String str) {
        String defaultMask = mask8;

        if (str.length() > 11){
            defaultMask = mask11;
        }
        return defaultMask;
    }
}
