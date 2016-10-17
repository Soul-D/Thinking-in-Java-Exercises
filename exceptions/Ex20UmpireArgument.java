package exceptions;

public class Ex20UmpireArgument extends Ex29BaseballException {
}

abstract class Ex20Inning {
    public Ex20Inning() throws Ex29BaseballException {}
    public void event() throws Ex29BaseballException {
        // Doesn't actually have to throw anything
    }
    public abstract void atBat() throws Strike, Foul, Ex20UmpireArgument;
    public void walk() {} // Throws no checked exceptions
}

class Ex20StormyInning extends Ex20Inning implements Storm {
    // OK to add new exceptions for constructors, but you
    // must deal with the base constructor exceptions:
    public Ex20StormyInning()
            throws RainedOut, Ex29BaseballException {}
    public Ex20StormyInning(String s)
            throws Foul, Ex29BaseballException {}
    // Regular methods must conform to base class:
//! void walk() throws PopFoul {} //Compile error
    // Interface CANNOT add exceptions to existing
    // methods from the base class:
//! public void event() throws RainedOut {}
    // If the method doesn't already exist in the
    // base class, the exception is OK:
    public void rainHard() throws RainedOut {}
    // You can choose to not throw any exceptions,
    // even if the base version does:
    public void event() {}
    // Overridden methods can throw inherited exceptions:
    public void atBat() throws PopFoul,Ex20UmpireArgument {}
    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch(PopFoul e) {
            System.out.println("Pop foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(Ex20UmpireArgument e){
            System.out.println("Ex20UmpireArgument");
        }
        catch(Ex29BaseballException e) {
            System.out.println("Generic baseball exception");
        }
        // Strike not thrown in derived version.
        try {
            // What happens if you upcast?
            Inning i = new StormyInning();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch(Strike e) {
            System.out.println("Strike");
        } catch(Foul e) {
            System.out.println("Foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(Ex20UmpireArgument e){
            System.out.println("Ex20UmpireArgument");
        } catch(Ex29BaseballException e) {
            System.out.println("Generic baseball exception");
        }
    }
} ///:~
