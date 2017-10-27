package afpa.dl101_filrouge_android.metier;

/**
 * Created by DL101 on 27/10/2017.
 */

public abstract class ToolBox {
    public static String padLeft(String s) {
        return "00".substring(s.length()) + s;
    }
}
