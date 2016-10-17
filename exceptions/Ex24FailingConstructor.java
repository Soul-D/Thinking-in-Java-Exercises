package exceptions;

public class Ex24FailingConstructor {
    Ex23Disposable d1;
    Ex23Disposable d2;
    public Ex24FailingConstructor(boolean flag) {
        d1 = new Ex23Disposable();
        try {
            if (flag)
                throw new RuntimeException();
            d2 = new Ex23Disposable();
            try{

            }
            catch(Exception e){
                d2.dispose();
            }
        }
        catch(Exception e){
            d1.dispose();
        }
    }
    void dispose(){
        d2.dispose();
        d1.dispose();
    }
}

class Ex24Test{
    public static void main(String[] args) {
        try {
            Ex24FailingConstructor fc = new Ex24FailingConstructor(true);
            try {
            } finally {
                fc.dispose();
            }
        } catch (Exception e) {

        }
    }
}
