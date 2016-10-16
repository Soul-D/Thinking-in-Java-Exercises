package holding;

import java.util.ArrayList;
import java.util.Iterator;

public class Ex8 {
    public static void main(String[] args) {
        ArrayList<Ex1Gerbil> ar = new ArrayList<Ex1Gerbil>();
        ar.add(new Ex1Gerbil(13));
        ar.add(new Ex1Gerbil(14));
        ar.add(new Ex1Gerbil(15));
        ar.add(new Ex1Gerbil(16));
        Iterator<Ex1Gerbil> it = ar.iterator();
        while (it.hasNext()){
            it.next().hop();
        }
        System.out.println();
        for (int i = 0; i < ar.size(); i++){
            ar.get(i).hop();
        }
    }
}
