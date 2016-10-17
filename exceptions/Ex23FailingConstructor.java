package exceptions;

public class Ex23FailingConstructor {
    public Ex23FailingConstructor(boolean flag) {
        Ex23Disposable d1 = new Ex23Disposable();
        try {
            if (flag)
                throw new RuntimeException();
            Ex23Disposable d2 = new Ex23Disposable();
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
}

class Ex23Disposable{
    static int i;
    final int id = i++;
    void dispose(){
        System.out.println("Disposing " + id);
    }
}

class Ex23Test{
    public static void main(String[] args) {
        try {
            new Ex23FailingConstructor(true);
            try {
            } finally {

            }
        } catch (Exception e) {

        }
    }
}

