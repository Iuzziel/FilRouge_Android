package afpa.dl101_filrouge_android.metier;

/**
 * Created by DL101 on 27/10/2017.
 */

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
}
