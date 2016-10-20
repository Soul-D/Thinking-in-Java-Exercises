package strings;

import java.util.Formatter;

public class Ex5 {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);
        f.format("%1$-+,(10d\n",10);
        f.format("%1$-10c\n",'x');
        f.format("%1$-10.1b\n",true);
        f.format("%1$-10.1s\n","hi");
        f.format("%1$-#+(,10.6f\n",1.32f);
        f.format("%1$-#+(10.6e\n",1.32E+02);
        f.format("%1$-10x\n",0x13);
        f.format("%1$-10.1h\n",0x13);
        f.format("%1$-10%\n");
    }
}
