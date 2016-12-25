package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex6Unsupported {
    static void test(String str, List<String> list){
        System.out.println("--------"+str+"--------");
        List<String> l = new ArrayList<String>(list.subList(2,2));
        try{
            list.add("12");
        }catch (Exception e){
            System.out.println("list.add()" + e);
        }
        try{
            list.addAll(l);
        }catch (Exception e){
            System.out.println("list.addAll()" + e);
        }
        try{
            list.remove(1);
        }catch (Exception e){
            System.out.println("list.remove()" + e);
        }
    }

    public static void main(String[] args) {
        test("Simple list",new ArrayList<String>(Arrays.asList("A","B","C","D")));
        test("Arrays.asList",Arrays.asList("A","B","C","D","E"));
        test("unmodifiableList", Collections.unmodifiableList(Arrays.asList("A","B","C","D")));
    }
}
