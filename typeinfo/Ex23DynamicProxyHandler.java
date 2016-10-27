//: typeinfo/SimpleDynamicProxy.java
package typeinfo; /* Added by Eclipse.py */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class Ex23DynamicProxyHandler implements InvocationHandler {
  private Object proxied;
  public Ex23DynamicProxyHandler(Object proxied) {
    this.proxied = proxied;
  }
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //System.out.println(proxy);
    return method.invoke(proxied, args);
  }
}

class Ex23SimpleDynamicProxy {
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
            new Ex23DynamicProxyHandler(real));
    consumer(proxy);
  }
}

