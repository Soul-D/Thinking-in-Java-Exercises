package holding;

import java.util.ArrayList;

public class Ex1Gerbil {
    int gerbilNumber;

    public Ex1Gerbil(int gerbilNumber) {
        this.gerbilNumber = gerbilNumber;
    }

    void hop(){
        System.out.println(gerbilNumber + " hopping");

    }
}

class Ex1Test{
    public static void main(String[] args) {
        ArrayList<Ex1Gerbil> ar = new ArrayList<Ex1Gerbil>();
        ar.add(new Ex1Gerbil(13));
        ar.add(new Ex1Gerbil(14));
        ar.add(new Ex1Gerbil(15));
        ar.add(new Ex1Gerbil(16));
        for (int i = 0; i < ar.size(); i++){
            ar.get(i).hop();
        }
    }
}
