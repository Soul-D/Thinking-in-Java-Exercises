package innerclasses;

import java.util.Random;

interface Ex17_Tossing {
    int tossing();
}

interface TossingFactory {
    Ex17_Tossing getTossing();
}

class DiceTossing implements Ex17_Tossing {
    public int tossing() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
    public static TossingFactory factory(){
        return new TossingFactory() {
            public Ex17_Tossing getTossing() {
                return new DiceTossing();
            }
        };
    }
}

class CoinTossing implements Ex17_Tossing {
    public int tossing() {
        Random random = new Random();
        return random.nextInt(2);
    }
    public static TossingFactory factory(){
        return new TossingFactory() {
            public Ex17_Tossing getTossing() {
                return new CoinTossing();
            }
        };
    }
}

class Ex17Test {
    static void toss(TossingFactory tf){
        System.out.println(tf.getTossing().tossing());
    }

    public static void main(String[] args) {
        toss(DiceTossing.factory());
        toss(CoinTossing.factory());
    }
}
