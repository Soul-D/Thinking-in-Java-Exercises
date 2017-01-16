package enumerated;

import java.util.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class Ex5VowelsAndConsonants {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a';
            printnb((char) c + ", " + c + ": ");
            Letters l = Letters.getType((char) c);
            switch (l) {
                case VOWEL:
                    print("vowel");
                    break;
                case SOMETIMES_A_VOWEL:
                    print("Sometimes a vowel");
                    break;
                case CONSONANT:
                    print("consonant");
                    break;
                default:
                    print("i don't know");
            }
        }
    }
}

enum Letters {
    VOWEL('a', 'e', 'i', 'o', 'u'),
    SOMETIMES_A_VOWEL('y', 'w'),
    CONSONANT('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z'),
    SOMETHING_ELSE();

    private Set<Character> values;

    @SuppressWarnings("unchecked")
    Letters(Character... chars) {
        values = new HashSet<Character>(Arrays.asList(chars));
    }

    public static Letters getType(char ch) {
        if (VOWEL.values.contains(ch))
            return VOWEL;
        if (SOMETIMES_A_VOWEL.values.contains(ch))
            return SOMETIMES_A_VOWEL;
        if (CONSONANT.values.contains(ch))
            return CONSONANT;
        return SOMETHING_ELSE;
    }
}