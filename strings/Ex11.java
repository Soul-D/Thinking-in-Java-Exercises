package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex11 {
    public static void main(String[] args) {
        String s = "Arline ate eight apples and one orange while Anita hadn't any";
        String regex = "(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b";
        Matcher m = Pattern.compile(regex).matcher(s);
        while(m.find()){
            System.out.println("Group = " + m.group());
        }
    }
}
