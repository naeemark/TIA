package com.zaeem.tia.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Naeem
 */

public class AppUtils {

    public static Spanned formatHTML(String htmlText) {

        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(htmlText);
        }
        return result;
    }

    public static boolean isValidName(CharSequence target) {

        Pattern p= Pattern.compile("^[\\p{L} .'-]+$");
        return p.matcher(target).matches();
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidPhone(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches();
    }

    public static boolean isValidAddress(CharSequence target) {
        return target.length() > 0;
    }
}
