package strings;

//http://www.rexegg.com/regex-best-trick.html trick with capturing only 1st group

import net.mindview.util.TextFile;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex19 {
    public static void main(String[] args) {
        String file = TextFile.read("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\strings\\Ex19.java"); //"Comment2"
        String s = "//notComment1";
        s = "/*notComment2*/";
        s = "//*notComment2*/"; //commentNew
        Matcher allLiterals = Pattern.compile("((['\"])(?:(?!\\2|\\\\).|\\\\.)*\\2)|\\/\\/[^\\n]*|\\/\\*(?:[^*]|\\*(?!\\/))*\\*\\/").matcher(file);
        while (allLiterals.find()) {
            file = file.replace(allLiterals.group()," ");
        }
        Set<String> classes = new HashSet<String>();
        Matcher allClasses = Pattern.compile("\\b\\p{Upper}\\w*").matcher(file);
        while (allClasses.find()){
            classes.add(allClasses.group());
        }
        System.out.println(classes);
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