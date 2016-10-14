package interfaces;

import java.util.Random;

interface Ex19_Tossing {
    int tossing();
}

interface TossingFactory {
    Ex19_Tossing getTossing();
}

class DiceTossing implements Ex19_Tossing {
    public int tossing() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

class CoinTossing implements Ex19_Tossing {
    public int tossing() {
        Random random = new Random();
        return random.nextInt(2);
    }
}

class DiceTossingFactory implements TossingFactory {
    public DiceTossing getTossing() {
        return new DiceTossing();
    }
}

class CoinTossingFactory implements TossingFactory {
    public CoinTossing getTossing() {
        return new CoinTossing();
    }
}

class Ex19Test {
    static void toss(TossingFactory tf){
        System.out.println(tf.getTossing().tossing());
    }

    public static void main(String[] args) {
        toss(new DiceTossingFactory());
        toss(new CoinTossingFactory());
    }
}
