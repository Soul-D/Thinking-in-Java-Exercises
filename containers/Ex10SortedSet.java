package containers;

import java.util.*;

import static net.mindview.util.Print.print;

public class Ex10SortedSet<E> implements SortedSet<E>{
    private LinkedList<E> list = new LinkedList<E>();
    private Comparator<? super E> comparator;

    public Ex10SortedSet(){}

    public Ex10SortedSet(Comparator<? super E> comparator){
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private int compare(Object k1, Object k2){
        return comparator != null ? (comparator.compare((E)k1,(E)k2)) : (((Comparable<E>)k1).compareTo((E)k2));
    }

    public Comparator<? super E> comparator() {
        return comparator;
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
        Ex10SortedSet<E> subSet = new Ex10SortedSet<E>(comparator);
        for (int i = 0; i < list.size(); i++){
            if (compare(list.get(i),toElement)>=0)
                break;
            if (compare(list.get(i),fromElement)>=0)
                subSet.list.add(list.get(i));
        }
        return subSet;
    }

    public SortedSet<E> headSet(E toElement) {
        return subSet(list.get(0),toElement);
    }

    public SortedSet<E> tailSet(E fromElement) {
        Ex10SortedSet<E> subSet = new Ex10SortedSet<E>(comparator);
        for (int i = 0; i < list.size(); i++){
            if (compare(list.get(i),fromElement)>=0)
                subSet.list.add(list.get(i));
        }
        return subSet;
    }

    public E first() {
        return list.get(0);
    }

    public E last() {
        return list.get(size()-1);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public boolean add(E e) {
        if (list.contains(e))
            return false;
        int index = -1;
        for (int i = 0; i < list.size(); i++){
            if (compare(e,list.get(i))<0) {
                index = i;
                break;
            }
        }
        list.add(index == -1 ? list.size() : index,e);
        return true;
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e : c){
            flag = flag || add(e);
        }
        return flag;
    }

    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

class Ex10Test{
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new Ex10SortedSet<String>();
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
        print(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for(int i = 0; i <= 6; i++) {
            if(i == 3) low = it.next();
            if(i == 6) high = it.next();
            else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));
        print(sortedSet.headSet(high));
        print(sortedSet.tailSet(low));
    }
}
