package strings; /* Added by Eclipse.py */
// "\main\java\strings\Ex15.java" "\b[Ssct]\w+" CASE_INSENSITIVE

import net.mindview.util.TextFile;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex15 {
  public static void main(String[] args) throws Exception {
    if(args.length < 3) {
      System.out.println("Usage: java JGrep file regex");
      System.exit(0);
    }
    String flag = args[2];
    int cnst = (Integer)Pattern.class.getDeclaredField(flag).get(null);
    Pattern p = Pattern.compile(args[1],cnst);
    // Iterate through the lines of the input file:
    int index = 0;
    Matcher m = p.matcher("");
    for(String line : new TextFile(args[0])) {
      m.reset(line);
      while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
    }
  }
} /* Output: (Sample)
0: strings: 4
1: simple: 10
2: the: 28
3: Ssct: 26
4: class: 7
5: static: 9
6: String: 26
7: throws: 41
8: System: 6
9: System: 6
10: compile: 24
11: through: 15
12: the: 23
13: the: 36
14: String: 8
15: System: 8
16: start: 31
*///:~
