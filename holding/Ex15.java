package holding;

import net.mindview.util.Stack;

public class Ex15 {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        String s = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        int i = 0;
        char[] arr = s.toCharArray();
        while(i!=s.length()){
            if(arr[i] == '+'){
                stack.push(arr[++i]);
            } else {
                System.out.print(stack.pop());
            }
            i++;
        }
    }
}
