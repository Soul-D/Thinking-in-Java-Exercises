package containers;

import java.util.*;

public class Ex33FastTraversalLinkedList<E> extends AbstractList<E> {
    private LinkedList<E> llist = new LinkedList<E>();
    private ArrayList<E> arlist = new ArrayList<E>();
    private boolean isArlistChanged = false;
    private boolean isLrlistChanged = false;

    @Override
    public boolean add(E e) {
        syncLL();
        isLrlistChanged = true;
        return llist.add(e);
    }

    @Override
    public void add(int index, E element) {
        syncLL();
        isLrlistChanged = true;
        llist.add(index,element);
    }

    @Override
    public E remove(int index) {
        syncLL();
        isLrlistChanged = true;
        return llist.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        syncLL();
        boolean res = llist.remove(o);
        if (res)
            isLrlistChanged = true;
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        syncAL();
        isArlistChanged = true;
        return arlist.iterator();
    }

    @Override
    public E get(int index) {
        syncAL();
        return arlist.get(index);
    }

    private void syncLL(){
        if (isArlistChanged) {
            llist = new LinkedList<E>(arlist);
            isArlistChanged = false;
        }
    }

    private void syncAL(){
        if (isLrlistChanged) {
            arlist = new ArrayList<E>(llist);
            isLrlistChanged = false;
        }
    }

    @Override
    public E set(int index, E element) {
        syncAL();
        isArlistChanged = true;
        return arlist.set(index, element);
    }

    @Override
    public void clear() {
        llist.clear();
        arlist.clear();
    }

    @Override
    public int size() {
        syncAL();
        syncLL();
        return arlist.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return !c.isEmpty();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        syncAL();
        isArlistChanged = true;
        return arlist.listIterator(index);
    }
}

class Ex33Test{
    public static void main(String[] args) {
        ListPerformance.ListTester.run(new ArrayList<Integer>(), ListPerformance.tests);
        ListPerformance.ListTester.run(new LinkedList<Integer>(), ListPerformance.tests);
        ListPerformance.ListTester.run(new Ex33FastTraversalLinkedList<Integer>(), ListPerformance.tests);
    }
}
