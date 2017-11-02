package afpa.dl101_filrouge_android.metier;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public abstract class ToolBox {
    public static String padLeft(String s) {
        return "00".substring(s.length()) + s;
    }

    public static String formatDate(String s) {
        String year = s.substring(0, 4);
        String month = s.substring(4, 6);
        String day = s.substring(6, 8);
        return day + "/" + month + "/" + year;
    }

    public static double ConvTempToFarenheit(double temperature) {
        return (temperature - 273) * (9 / 5) + 32;
    }

    public static double ConvTempToCelsius(double temperature) {
        return (temperature - 273.15);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
