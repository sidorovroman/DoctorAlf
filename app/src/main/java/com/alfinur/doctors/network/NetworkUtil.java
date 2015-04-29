package com.alfinur.doctors.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alfinur.doctors.MyApplication;

public class NetworkUtil {

    public enum ConnectionType {

        TYPE_WIFI(1),
        TYPE_MOBILE(2),
        TYPE_NOT_CONNECTED(0);

        private ConnectionType(Integer type){
            this.type = type;
        }
        private Integer type;

        public Integer getType() {
            return type;
        }
    }


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return ConnectionType.TYPE_WIFI.getType();

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return ConnectionType.TYPE_MOBILE.getType();
        }
        return ConnectionType.TYPE_NOT_CONNECTED.getType();
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == ConnectionType.TYPE_WIFI.getType()) {
            status = "Wifi enabled";
        } else if (conn == ConnectionType.TYPE_MOBILE.getType()) {
            status = "Mobile data enabled";
        } else if (conn == ConnectionType.TYPE_NOT_CONNECTED.getType()) {
            status = "Not connected to Internet";
        }
        return status;
    }
    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}