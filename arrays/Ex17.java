package arrays;

import net.mindview.util.Generated;
import net.mindview.util.Generator;

import java.math.BigDecimal;
import java.util.Arrays;

class BigDecimalGenerator implements Generator<BigDecimal>{
    private BigDecimal increment;
    private BigDecimal bd;

    public BigDecimalGenerator(BigDecimal increment) {
        this.increment = increment;
        bd = increment.multiply(new BigDecimal(-1));
    }

    public BigDecimal next() {
        bd = bd.add(increment);
        return bd;
    }
}

public class Ex17 {
    public static void main(String[] args) {
        BigDecimal[] bd = Generated.array(BigDecimal.class,new BigDecimalGenerator(new BigDecimal(1.5)),15);
        System.out.println(Arrays.toString(bd));
        BigDecimal[] bd2 = new BigDecimal[13];
        Generated.array(bd2,new BigDecimalGenerator(new BigDecimal(3)));
        System.out.println(Arrays.toString(bd2));
    }
}
