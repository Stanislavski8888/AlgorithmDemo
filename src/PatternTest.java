import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        String src = "÷È×å1927to1939-2018-04-10 19:35";
        String regex = "\\-\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        //        System.out.println(matcher.find());
        if(matcher.find()) {
            System.out.println("start = " + matcher.start()+", end = "+matcher.end());
            System.out.println(src.substring(0, matcher.start()));
        }
    }

}
