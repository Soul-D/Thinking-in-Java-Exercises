package arrays;

import java.util.ArrayList;
import java.util.List;

public class Ex9 {
    public static void main(String[] args) {
        //Peel<Banana>[] peels = new Peel<Banana> [10] ;
        List<Peel<Banana>> peels = new ArrayList<Peel<Banana>>(10);
    }
}

class Banana{}

class Peel<T>{}
