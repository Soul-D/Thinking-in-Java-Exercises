package generics;

public class Ex30 {
    public static void main(String[] args) {
        Holder<Integer> hI = new Holder<Integer>();
        hI.set(13);
        int x = hI.get();
        Holder<Long> hL = new Holder<Long>();
        hI.set(13);
        long l = hI.get();
        Holder<Boolean> hB = new Holder<Boolean>();
        hB.set(true);
        boolean b = hB.get();
        Holder<Float> hF = new Holder<Float>();
        hF.set(13f);
        float f = hF.get();
        Holder<Double> hD = new Holder<Double>();
        hD.set(13.4);
        double d = hD.get();
        Holder<Byte> hBt = new Holder<Byte>();
        hBt.set((byte)1);
        byte bt = hBt.get();
        Holder<Character> hC = new Holder<Character>();
        hC.set('r');
        char c = hC.get();
        Holder<Short> hS = new Holder<Short>();
        hS.set((short)13);
        short s = hS.get();
    }
}
