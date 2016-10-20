package strings;

//http://www.rexegg.com/regex-best-trick.html trick with capturing only 1st group

import net.mindview.util.TextFile; //Comment1

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex17 {
    public static void main(String[] args) {
        String file = TextFile.read("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\strings\\Ex17.java"); //"Comment2"
        String s = "//notComment1";
        s = "/*notComment2*/";
        s = "//*notComment2*/"; //commentNew
        Matcher allComments = Pattern.compile("((['\"])(?:(?!\\2|\\\\).|\\\\.)*\\2)|\\/\\/[^\\n]*|\\/\\*(?:[^*]|\\*(?!\\/))*\\*\\/").matcher(file);
        while (allComments.find()) {
            if (allComments.group(1) == null) {
                System.out.println(allComments.group());
            }
        }
    }
}
//"\"(.*?)\""
//"\\"(.*?)\""
//*/
/*"comment3"
*comment4
*/
/****"javaDoc1"
******javaDoc2
******/
/*"comment5"
//comment6
*/