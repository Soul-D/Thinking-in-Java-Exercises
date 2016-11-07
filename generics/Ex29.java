package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ex29 {
    static void m1(Holder<List<?>> arg){
        arg.set(new ArrayList<Fibonacci>());
        //List<Fibonacci> list = arg.get();
        //List<Object> list = arg.get();
        List list = arg.get();
        arg.equals(new HashMap<Integer, Integer>());
        //arg.get().add(new Object());
        Object obj = arg.get().get(0);
        //Fibonacci fib = arg.get().get(0);
    }

    static void m2(List<Holder<?>> arg){
        arg.add(new Holder<Object>());
        arg.add(new Holder<Fibonacci>());
        Holder holder = arg.get(0);
        //Holder<Object> holder2 = arg.get(0);
        //arg.get(0).set(new Holder<Object>())
        //arg.get(0).set(new Holder<Fibonacci>())
        Object obj = arg.get(0).get();
        //Fibonacci fib = arg.get(0).get();
    }
}
