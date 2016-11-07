package generics;

interface Ex23FactoryI<T, K> {
    T create(K arg);
}

class Ex23Foo2<T, K> {
    private T x;
    public <F extends Ex23FactoryI<T, K>> Ex23Foo2(F factory, K arg) {
        x = factory.create(arg);
    }
}

class Ex23IntegerFactory implements Ex23FactoryI<Integer, Integer> {
    public Integer create(Integer arg) {
        return new Integer(arg);
    }
}

class Ex23Widget {
    public Ex23Widget(Integer i) {
        System.out.println(i);
    }

    public static class Ex23Factory implements Ex23FactoryI<Ex23Widget, Integer> {
        public Ex23Widget create(Integer arg) {
            return new Ex23Widget(arg);
        }
    }
}

class Ex23FactoryConstraint {
    public static void main(String[] args) {
        new Ex23Foo2<Integer, Integer>(new Ex23IntegerFactory(),13);
        new Ex23Foo2<Ex23Widget, Integer>(new Ex23Widget.Ex23Factory(),15);
    }
}

