package strings;

import java.util.regex.Pattern;

public class Ex7 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\p{Upper}.*[.]");
        System.out.println(pattern.matcher("Q1fvd wd fdf.").matches());
    }
}
