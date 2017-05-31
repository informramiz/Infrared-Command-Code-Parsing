import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by ramiz on 5/31/17.
 */
public class MyUtils {
    public static boolean isNumeric(String str) {
        if (str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); ++i) {
            if(!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String listToString(ArrayList<Integer> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining("."));
    }
}
