package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Ex7 {
    public static void main(String[] args) {
        int[] arr = new int[0];
        try{
            arr[1] = 5;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Logger.getLogger("Ex7").severe(sw.toString());
        }
    }
}
