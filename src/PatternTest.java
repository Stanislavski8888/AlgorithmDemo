import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        PatternTest instance = new PatternTest();
        String src = "1927to1939-2018-04-10 19:35";
        String regex = "\\-\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        //        System.out.println(matcher.find());
        if(matcher.find()) {
            System.out.println("start = " + matcher.start()+", end = "+matcher.end());
            System.out.println(src.substring(0, matcher.start()));
        }

        int result = instance.testException();
        System.out.println("result = " + result);
    }


    private void test() throws MyExcetion {
        throw new MyExcetion();
    }
    private int testException() {
        try {
            test();
            return 1;
        } catch (MyExcetion e) {
            e.printStackTrace();
//            return -1;
        }
        return 0;
    }
}

class MyExcetion extends Exception {
    MyExcetion() {
        super("throw MyException !");
    }
}
