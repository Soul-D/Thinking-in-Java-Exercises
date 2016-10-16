package holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Ex7 {
    static Ex7[] array = {new Ex7(),new Ex7(),new Ex7(),new Ex7(),new Ex7()};
    static Collection fill(Collection collection){
        for(int i = 0; i < array.length; i++){
            collection.add(array[i]);
        }
        return collection;
    }
}

class Ex7Test{
    public static void main(String[] args) {
        List list = new ArrayList();
        Ex7.fill(list);
        System.out.println(list);
        List subList = list.subList(1,3);
        //list.removeAll(subList);
        subList.remove(0);
        System.out.println(list);
        list.removeAll(subList);
        System.out.println(list);
    }
}
