//: typeinfo/SimpleProxyDemo.java
package typeinfo; /* Added by Eclipse.py */

import java.util.Date;

import static net.mindview.util.Print.print;

class Ex21SimpleProxy implements Interface {
  private Interface proxied;
  public Ex21SimpleProxy(Interface proxied) {
    this.proxied = proxied;
  }
  public void doSomething() {
    long start = System.nanoTime();
    proxied.doSomething();
    System.out.println("Tine in ns = " + (System.nanoTime() - start));
  }
  public void somethingElse(String arg) {
    long start = System.nanoTime();
    proxied.somethingElse(arg);
    System.out.println("Tine in ns = " + (System.nanoTime() - start));
  }
}

class Ex21SimpleProxyDemo {
  public static void consumer(Interface iface) {
    iface.doSomething();
    iface.somethingElse("bonobo");
  }
  public static void main(String[] args) {
    consumer(new RealObject());
    consumer(new Ex21SimpleProxy(new RealObject()));
  }
}

