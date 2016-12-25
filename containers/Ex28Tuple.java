package containers;

public class Ex28Tuple {
    public static class T2<A, B> implements Comparable<T2<A, B>>{
        private A first;
        private B second;

        public T2(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(T2<A, B> o) {
            int res = ((Comparable<A>)first).compareTo(o.first);
            if (res != 0)
                return res;
            return ((Comparable<B>)second).compareTo(o.second);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            T2<?, ?> t2 = (T2<?, ?>) o;

            if (first != null ? !first.equals(t2.first) : t2.first != null) return false;
            return second != null ? second.equals(t2.second) : t2.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

    public static class T3<A, B, C> implements Comparable<T3<A, B, C>>{
        private T2<A, B> tuple;
        private C third;

        public T3(A first, B second, C third) {
            tuple = new T2<A, B>(first,second);
            this.third = third;
        }

        public A getFirst() {
            return tuple.getFirst();
        }

        public B getSecond() {
            return tuple.getSecond();
        }

        public C getThird() {
            return third;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            T3<?, ?, ?> t3 = (T3<?, ?, ?>) o;

            if (tuple != null ? !tuple.equals(t3.tuple) : t3.tuple != null) return false;
            return third != null ? third.equals(t3.third) : t3.third == null;

        }

        @Override
        public int hashCode() {
            int result = tuple != null ? tuple.hashCode() : 0;
            result = 31 * result + (third != null ? third.hashCode() : 0);
            return result;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(T3<A, B, C> o) {
            int res = tuple.compareTo(o.tuple);
            if (res != 0)
                return res;
            return ((Comparable<C>)third).compareTo(o.third);
        }
    }

    public static class T4<A, B, C, D> implements Comparable<T4<A, B, C, D>>{
        T3<A, B, C> tuple;
        D forth;

        public T4(A a, B b, C c, D d){
            tuple = new T3<A, B, C>(a,b,c);
            forth = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            T4<?, ?, ?, ?> t4 = (T4<?, ?, ?, ?>) o;

            if (tuple != null ? !tuple.equals(t4.tuple) : t4.tuple != null) return false;
            return forth != null ? forth.equals(t4.forth) : t4.forth == null;

        }

        @Override
        public int hashCode() {
            int result = tuple != null ? tuple.hashCode() : 0;
            result = 31 * result + (forth != null ? forth.hashCode() : 0);
            return result;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(T4<A, B, C, D> o) {
            int res = tuple.compareTo(o.tuple);
            if (res != 0)
                return res;
            return ((Comparable<D>)forth).compareTo(o.forth);
        }

        public A getFirst() {
            return tuple.getFirst();
        }

        public B getSecond() {
            return tuple.getSecond();
        }

        public C getThird() {
            return tuple.getThird();
        }

        public D getForth() {
            return forth;
        }
    }

    public static class T5<A, B, C, D, E> implements Comparable<T5<A, B, C, D, E>>{
        T4<A, B, C, D> tuple;
        E fifth;

        public T5(A a, B b, C c, D d, E e){
            tuple = new T4<A, B, C, D>(a,b,c,d);
            fifth = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            T5<?, ?, ?, ?, ?> t5 = (T5<?, ?, ?, ?, ?>) o;

            if (tuple != null ? !tuple.equals(t5.tuple) : t5.tuple != null) return false;
            return fifth != null ? fifth.equals(t5.fifth) : t5.fifth == null;

        }

        @Override
        public int hashCode() {
            int result = tuple != null ? tuple.hashCode() : 0;
            result = 31 * result + (fifth != null ? fifth.hashCode() : 0);
            return result;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(T5<A, B, C, D, E> o) {
            int res = tuple.compareTo(o.tuple);
            if (res != 0)
                return res;
            return ((Comparable<E>)fifth).compareTo(o.fifth);
        }

        public A getFirst() {
            return tuple.getFirst();
        }

        public B getSecond() {
            return tuple.getSecond();
        }

        public C getThird() {
            return tuple.getThird();
        }

        public D getForth() {
            return tuple.getForth();
        }

        public E getFifth() {
            return fifth;
        }
    }

    public static <A, B> T2<A, B> tuple(A a, B b) {
        return new T2<A, B>(a, b);
    }

    public static <A, B, C> T3<A, B, C> tuple(A a, B b, C c) {
        return new T3<A, B, C>(a, b, c);
    }

    public static <A, B, C, D> T4<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new T4<A, B, C, D>(a, b, c, d);
    }

    public static <A, B, C, D, E> T5<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
        return new T5<A, B, C, D, E>(a, b, c, d, e);
    }
}


