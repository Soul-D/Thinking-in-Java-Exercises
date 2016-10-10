//: control/IfElse2.java
package control; /* Added by Eclipse.py */

import static net.mindview.util.Print.print;

public class Ex6 {
    static boolean test(int testval, int begin, int end) {
        if ((testval >= begin) && (testval <= end)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        print(test(10, 1, 5));
        print(test(5, 1, 10));
        print(test(5, 5, 8));
    }
} /* Output:
1
-1
0
*///:~
