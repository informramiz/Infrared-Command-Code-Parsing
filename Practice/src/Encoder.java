import java.util.ArrayList;

/**
 * Created by ramiz on 5/31/17.
 */
public class Encoder {
    //keywords used on infrared command code to mark start of something
    public static final String KEYWORD_PREFIX = "UIR";
    public static final String KEYWORD_FREQUENCY = "FREQ";
    public static final String KEYWORD_REPEAT = "REPEAT";
    public static final String KEYWORD_DELAY = "DELAY";
    public static final String KEYWORD_SEQUENCE = "SEQ";
    //delimiter character used in command string code
    //dot is a special character in regex so we have to escape it
    public static final String DELIMITER = "\\.";

    public static String encode(Command command) {
        StringBuilder stringBuilder = new StringBuilder();

        //append prefix
        stringBuilder.append(KEYWORD_PREFIX);
        stringBuilder.append(".");

        //append frequency keyword and value
        stringBuilder.append(KEYWORD_FREQUENCY);
        stringBuilder.append(".");
        stringBuilder.append(command.frequency);
        stringBuilder.append(".");

        //as repeat count is optional so first check then append
        if (command.repeatCount != null) {
            //append repeat count keyword and value
            stringBuilder.append(KEYWORD_REPEAT);
            stringBuilder.append(".");
            stringBuilder.append(command.repeatCount);
            stringBuilder.append(".");
        }

        //as delay is optional so first check then append
        if (command.delay != null) {
            //append delay keyword and value
            stringBuilder.append(KEYWORD_DELAY);
            stringBuilder.append(".");
            stringBuilder.append(command.delay);
            stringBuilder.append(".");
        }

        //append each sequence one by one keyword and value
        for (ArrayList<Integer> sequence : command.sequences) {
            stringBuilder.append(KEYWORD_SEQUENCE);
            stringBuilder.append(".");
            stringBuilder.append(MyUtils.listToString(sequence));

            //append dot if current sequence is not the last sequence
            if (!(sequence == command.sequences.get(command.sequences.size() - 1))) {
                stringBuilder.append(".");
            }
        }

        return stringBuilder.toString();
    }

    public static Command decode(String str) {
        //check validity of code
        if (!isValidCode(str)) {
            return null;
        }

        //object to store parsed data
        Command command = new Command();
        //variable to hold current keyword being parsed
        String currentKeyword = "";
        //variable to store number of sequences processed
        int parsedSequencesCount = 0;

        //tokenize the code based on code delimiter
        String[] tokens = str.split(DELIMITER);

        for (String token : tokens) {
            //as our first keyword is always prefix so let's just ignore it
            if (isPrefixKeyword(token)) {
                continue;
            }

            //if next token is a keyword then mark the start of that keyword and continue
            if (isKeyword(token)) {
                //if the keyword before this new keyword was sequence-keyword then
                //we have successfully parsed a sequence so we should increment the count
                //of sequences parsed by 1
                if (isSequenceKeyword(currentKeyword)) {
                    parsedSequencesCount++;
                }

                //mark the new keyword as current keyword being parsed
                currentKeyword = token;
                continue;
            }

            //token is not a keyword so add it to our object
            addKeywordValue(currentKeyword, token, parsedSequencesCount, command);
        }

        return command;
    }

    /**
     * Based on current keyword, this function adds the value to command object
     *
     * @param keyword current keyword being processed
     * @param value value of that keyword to be parsed
     * @param command object to store/add value to
     */
    public static void addKeywordValue(String keyword, String value, int parsedSequencesCount, Command command) {
        //assuming that all values are integer
        Integer intValue = Integer.parseInt(value);

        //check current keyword being parsed and add value accordingly
        if (isFrequencyKeyword(keyword)) {
            command.frequency = intValue;
        } else if (isRepeatKeyword(keyword)) {
            command.repeatCount = intValue;
        } else if (isDelayKeyword(keyword)) {
            command.delay = intValue;
        } else if (isSequenceKeyword(keyword)) {
            //check if this is a new sequence parsing turn
            //and if yes then we have to initialize ArrayList for this turn first
            if(command.sequences.size() == parsedSequencesCount) {
                command.sequences.add(new ArrayList<>());
            }
            command.sequences.get(parsedSequencesCount).add(intValue);
        }
    }

    public static boolean isValidCode(String str) {
        //code must
        //1. start with prefix keyword
        //2. include frequency
        //3. include at least 1 sequence
        return str.startsWith(KEYWORD_PREFIX) && str.contains(KEYWORD_FREQUENCY)
                && str.contains(KEYWORD_SEQUENCE);
    }

    public static boolean isKeyword(String str) {
        return isPrefixKeyword(str) || isFrequencyKeyword(str)
                || isRepeatKeyword(str) || isDelayKeyword(str)
                || isSequenceKeyword(str);
    }

    public static boolean isPrefixKeyword(String str) {
        return  str.equalsIgnoreCase(KEYWORD_PREFIX);
    }

    public static boolean isFrequencyKeyword(String str) {
        return  str.equalsIgnoreCase(KEYWORD_FREQUENCY);
    }

    public static boolean isRepeatKeyword(String str) {
        return  str.equalsIgnoreCase(KEYWORD_REPEAT);
    }

    public static boolean isDelayKeyword(String str) {
        return  str.equalsIgnoreCase(KEYWORD_DELAY);
    }

    public static boolean isSequenceKeyword(String str) {
        return  str.equalsIgnoreCase(KEYWORD_SEQUENCE);
    }
}
