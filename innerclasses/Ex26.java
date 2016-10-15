package innerclasses;

public class Ex26 {
    class Inner{
        Inner(int i){
            System.out.println(i);
        }
    }
}

class Ex26Second {
    class Inner extends Ex26.Inner{
        public Inner(Ex26 ex26) {
            ex26.super(13);
        }
    }
}

class Ex26Test {
    public static void main(String[] args) {
        Ex26 ex26 = new Ex26();
        //Ex26Second ex26second = new Ex26Second();
        //Ex26Second.Inner inner2 = ex26second.new Inner(ex26);
        Ex26Second.Inner inner = new Ex26Second().new Inner(new Ex26());
    }
}
