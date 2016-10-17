package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Ex6Exception1 extends Exception{
    Logger logger = Logger.getLogger("Ex6Exception1");
    public Ex6Exception1() {
        StringWriter sw = new StringWriter();
        printStackTrace(new PrintWriter(sw));
        logger.severe(sw.toString());
    }
}

class Ex6Exception2 extends Exception{
    Logger logger = Logger.getLogger("Ex6Exception2");
    public Ex6Exception2() {
        StringWriter sw = new StringWriter();
        printStackTrace(new PrintWriter(sw));
        logger.severe(sw.toString());
    }
}

class Ex6Test{
    public static void main(String[] args) {
        try{
            throw new Ex6Exception1();
        }
        catch (Ex6Exception1 e){

        }
        try{
            throw new Ex6Exception2();
        }
        catch (Ex6Exception2 e){

        }
    }
}
