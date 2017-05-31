import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by ramiz on 5/31/17.
 */
public class Command {
    //fields that are important for us to convert to
    public int frequency;

    //as repeat count field is optional so null value is considered an indication of
    //repeat count not present in code
    public Integer repeatCount = null;

    //as delay field is optional so null value is considered an indication of
    //repeat count not present in code
    public Integer delay = null;

    //there can be multiple sequences and at least 1 sequence
    //so an array of integer arrays is more suitable for this
    public ArrayList<ArrayList<Integer>> sequences = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FEQ: " + frequency + ", ");
        stringBuilder.append("Repeat: " + repeatCount + ", ");
        stringBuilder.append("Delay: " + delay);

        for (ArrayList<Integer> sequence : sequences) {
            stringBuilder.append(", SEQ: ");
            //convert sequence to dot separated string using Java8 lambdas
            String strSequence = MyUtils.listToString(sequence);

            stringBuilder.append(strSequence);
        }

        return stringBuilder.toString();
    }
}
