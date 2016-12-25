package containers;

import java.util.*;

public class Ex24SimpleHashSet<K> implements Set<K>{
    private final Object obj = new Object();
    private final Ex23SimpleHashMap<K,Object> map = new Ex23SimpleHashMap<K, Object>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    public Iterator<K> iterator() {
        return map.keySet().iterator();
    }

    public Object[] toArray() {
        return map.keySet().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return map.keySet().toArray(a);
    }

    public boolean add(K k) {
        map.put(k,obj);
        return true;
    }

    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    public boolean containsAll(Collection<?> c) {
        return map.keySet().containsAll(c);
    }

    public boolean addAll(Collection<? extends K> c) {
        for (K elem : c) {
            map.put(elem,obj);
        }
        return c.size() != 0;
    }

    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        for (Map.Entry<K, Object> entry : map.entrySet()) {
            if (!c.contains(entry.getKey())){
                flag = flag || map.remove(entry.getKey()) != null;
            }
        }
        return flag;
    }

    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object elem : c) {
            flag = flag || map.remove(elem) != null;
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

class Ex24Test{
    public static void main(String[] args) {
        Ex24SimpleHashSet<Integer> set = new Ex24SimpleHashSet<Integer>();
        set.addAll(Arrays.asList(8,3,2,9,5,3,0,56));
        System.out.println(set);
        set.clear();
        System.out.println(set);
    }
}
