package pl.sdacademy.networkconnections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTool {

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected()
                && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
    }
}
