package containers;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Ex18SlowSet<E> implements Set<E> {
    final Object obj = new Object();
    Ex17SlowMap<E,Object> map = new Ex17SlowMap<E, Object>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public Object[] toArray() {
        return map.keySet().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return map.keySet().toArray(a);
    }

    public boolean add(E e) {
        map.put(e,obj);
        return true;
    }

    public boolean remove(Object o) {
        if (!map.containsKey(o))
            return false;
        map.remove(o);
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        return map.keySet().containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c){
            map.put(e,obj);
        }
        return c.size()!=0;
    }

    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()){
            E current = iterator.next();
            if (!c.contains(current)) {
                iterator.remove();
                flag = true;
            }
        }
        return flag;
    }

    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object e : c){
            flag = flag || map.remove(e) != null;
        }
        return flag;
    }

    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }
}

class Ex18Test{
    public static void main(String[] args) {
        Set<Integer> set = new Ex18SlowSet<Integer>();
        for (int i = 0; i < 13; i++){
            set.add(i);
        }
        for (int i = 0; i < 13; i++){
            set.add(i);
        }
        System.out.println(set);
        set.remove(4);
        System.out.println(set);
    }
}
