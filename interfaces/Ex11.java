package interfaces;

import interfaces.interfaceprocessor.Apply;
import interfaces.interfaceprocessor.Processor;

public class Ex11 {
    String swap(String arg){
        char[] arr = arg.toCharArray();
        for (int i = 1; i < arr.length; i+=2){
            char tmp = arr[i-1];
            arr[i-1] = arr[i];
            arr[i] = tmp;
        }
        return new String(arr);
    }
}

class SwapAdapter implements Processor{
    private Ex11 e;

    public SwapAdapter(Ex11 e) {
        this.e = e;
    }

    public String name() {
        return Ex11.class.getSimpleName();
    }

    public String process(Object input) {
        return e.swap((String)input);
    }
}

class Ex11_Test {
    public static void main(String[] args) {
        Apply.process(new SwapAdapter(new Ex11()),"1234567");
    }
}
