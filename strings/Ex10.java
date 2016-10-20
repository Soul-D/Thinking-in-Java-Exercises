package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex10 {
    public static void main(String[] args) {
        String s = "Java now has regular expressions";
        String[] regexs = {"Java",
                "\\Breg.*",
                "n.w\\s+h(a|i)s",
                "s?",
                "s*",
                "s+",
                "s{4}",
                "s{1}.",
                "s{0,3}"};
        for (String regex : regexs){
            System.out.println("Regex = " + regex);
            Matcher m = Pattern.compile(regex).matcher(s);
            while(m.find()){
                System.out.println("Group = " + m.group() + " start = " + m.start() + " end " + m.end());
            }
        }
    }
}
