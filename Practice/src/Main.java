/**
 * Created by ramiz on 5/31/17.
 */
public class Main {

    public static void main(String args[]) {

        String code1 = "UIR.FREQ.38000.REPEAT.2.DELAY.1000.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";
        //remove optional fields from code to check code correctness and handling
        String code2 = "UIR.FREQ.38000.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";
        //change the order of fields to check if code correctly handles any order: Take frequence field to end
        String code3 = "UIR.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.FREQ.38000";
        //let's change order and bring the frequence in the middle of sequence fields
        String code4 = "UIR.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.FREQ.38000.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";

        Command command1 = Encoder.decode(code1);
        Command command2 = Encoder.decode(code2);
        Command command3 = Encoder.decode(code3);
        Command command4 = Encoder.decode(code4);

        System.out.println("Code-1 decoded: " + command1);
        System.out.println("Code-2 decoded: " + command2);
        System.out.println("Code-3 decoded: " + command3);
        System.out.println("Code-4 decoded: " + command4);

        String command1Encoded = Encoder.encode(command1);
        String command2Encoded = Encoder.encode(command2);
        String command3Encoded = Encoder.encode(command3);
        String command4Encoded = Encoder.encode(command4);

        System.out.println("Command1 Encoded: " + command1Encoded);
        System.out.println("Command2 Encoded: " + command2Encoded);
        System.out.println("Command3 Encoded: " + command3Encoded);
        System.out.println("Command4 Encoded: " + command3Encoded);

        //tests
        System.out.println(code1.equals(command1Encoded) == true);
        System.out.println(code2.equals(command2Encoded) == true);
        //as command3 and command2 are same so they should be equal after encoding
        //this is because encoder follows a specific order while encoding which is same as command1 and command2
        System.out.println(command2Encoded.equals(command3Encoded) == true);
        //as command2 and command4 are same so they should be equal after encoding
        //this is because encoder follows a specific order while encoding which is same as command1 and command2
        System.out.println(command4Encoded.equals(command3Encoded) == true);
    }

    public static void otherTests() {
        String code = "UIR.FREQ.38000.REPEAT.2.DELAY.1000.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";

        //should be true
        System.out.println(MyUtils.isNumeric("12345") == true);
        //should be false
        System.out.println(MyUtils.isNumeric("1r345") == false);

        //should be true
        System.out.println(Encoder.isValidCode(code) == true);
        //should be false
        System.out.println(Encoder.isValidCode(code.replace("FREQ.", "")) == false);

        System.out.println(Encoder.isKeyword(Encoder.KEYWORD_PREFIX) == true);
        System.out.println(Encoder.isKeyword(Encoder.KEYWORD_FREQUENCY) == true);
        System.out.println(Encoder.isKeyword(Encoder.KEYWORD_REPEAT) == true);
        System.out.println(Encoder.isKeyword(Encoder.KEYWORD_DELAY) == true);
        System.out.println(Encoder.isKeyword(Encoder.KEYWORD_SEQUENCE) == true);
        System.out.println(Encoder.isKeyword("FRQ") == false);
    }
}
