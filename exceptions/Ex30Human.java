//: exceptions/Human.java
package exceptions; /* Added by Eclipse.py */


class Ex30Annoyance extends RuntimeException {
}

class Ex30Sneeze extends Ex30Annoyance {
}

public class Ex30Human {
    static void throwRuntime(int i){
        try {
            switch (i) {
                case 0:
                    throw new Ex30Annoyance();
                case 1:
                    throw new Ex30Sneeze();
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            throwRuntime(0);
        }
        catch(Exception e){
            try {
                throw e.getCause();
            }
            catch (Ex30Sneeze e1){
                e1.printStackTrace();
            }
            catch (Ex30Annoyance e1){
                e1.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            throwRuntime(1);
        }
        catch(Exception e){
            try {
                throw e.getCause();
            }
            catch (Ex30Sneeze e1){
                e1.printStackTrace();
            }
            catch (Ex30Annoyance e1){
                e1.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}