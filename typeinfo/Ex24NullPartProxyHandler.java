package typeinfo;

import net.mindview.util.Null;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Ex24NullPartProxyHandler implements InvocationHandler {
    private Class<? extends IPart> clazz;
    private NPart nPart = new NPart();

    public Ex24NullPartProxyHandler(Class<? extends IPart> clazz) {
        this.clazz = clazz;
    }

    private class NPart implements Null, IPart{
        @Override
        public String toString() {
            return "NULL " + clazz.getSimpleName();
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(nPart,args);
    }
}

class NullPart{
    public static IPart newPart(Class<? extends IPart> clazz){
        return (IPart) Proxy.newProxyInstance(
                IPart.class.getClassLoader(),
                new Class[]{Null.class, IPart.class},
                new Ex24NullPartProxyHandler(clazz)
        );
    }

    public static class Factory implements typeinfo.factory.Factory<IPart>{
        public IPart create() {
            return newPart(Ex24FuelFilter.class);
        }
    }
}

class Ex24FuelFilter implements IPart {
    // Create a Class Factory for each specific type:
    public static class Factory
            implements typeinfo.factory.Factory<FuelFilter> {
        public FuelFilter create() { return new FuelFilter(); }
    }
}

interface IPart{}

class Ex24Test{
    public static void main(String[] args) {
        System.out.println(new Ex24FuelFilter());
        System.out.println(new NullPart.Factory().create());
    }
}