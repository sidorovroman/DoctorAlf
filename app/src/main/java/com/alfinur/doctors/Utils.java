package com.alfinur.doctors;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.Locale;

public class Utils {
    public static String getUserAgent(Context context) {
        String packageName = null;
        int packageVersion = 0;
        try {
            packageName = context.getPackageName();
            packageVersion = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder userAgent = new StringBuilder()
                .append(packageName)
                .append("/")
                .append(packageVersion);

        Locale locale = Locale.getDefault();
        if (locale != null) {
            userAgent.append(" (").append(locale.toString()).append("); ");
        }
        userAgent.append("Android/").append(Build.VERSION.RELEASE).append("; ");
        userAgent.append(Build.MODEL).append("/").append(Build.VERSION.SDK_INT).append(";");
        return userAgent.toString();
    }
}