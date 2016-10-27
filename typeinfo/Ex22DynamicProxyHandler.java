//: typeinfo/SimpleDynamicProxy.java
package typeinfo; /* Added by Eclipse.py */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class Ex22DynamicProxyHandler implements InvocationHandler {
  private Object proxied;
  public Ex22DynamicProxyHandler(Object proxied) {
    this.proxied = proxied;
  }
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    long start = System.nanoTime();
    Object result = method.invoke(proxied, args);
    System.out.println("Tine in ns = " + (System.nanoTime() - start));
    return result;
  }
}

class Ex22SimpleDynamicProxy {
  public static void consumer(Interface iface) {
    iface.doSomething();
    iface.somethingElse("bonobo");
  }
  public static void main(String[] args) {
    RealObject real = new RealObject();
    consumer(real);
    // Insert a proxy and call again:
    Interface proxy = (Interface) Proxy.newProxyInstance(
            Interface.class.getClassLoader(),
            new Class[]{ Interface.class },
            new Ex22DynamicProxyHandler(real));
    consumer(proxy);
  }
}

