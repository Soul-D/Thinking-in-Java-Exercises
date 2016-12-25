package containers;

import net.mindview.util.Countries;

import java.util.*;

public class Ex7 {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>(Countries.names(10));
        LinkedList<String> ll = new LinkedList<String>(Countries.names(20).subList(10,20));
        printList(al);
        printList(ll);
        addListToList(al,ll);
        printList(al);
        addListToListBackwards(al,ll);
        printList(al);
    }

    static <E> void printList(List<E> list){
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    static <E> void addListToList(List<E> to, List<E> from){
        ListIterator<E> toListIterator = to.listIterator();
        ListIterator<E> fromListIterator = from.listIterator();
        while(toListIterator.hasNext()){
            toListIterator.next();
            if (fromListIterator.hasNext())
                toListIterator.add(fromListIterator.next());
        }
    }

    static <E> void addListToListBackwards(List<E> to, List<E> from){
        ListIterator<E> toListIterator = to.listIterator(to.size());
        ListIterator<E> fromListIterator = from.listIterator();
        while(toListIterator.hasPrevious()){
            toListIterator.previous();
            if (fromListIterator.hasNext())
                toListIterator.add(fromListIterator.next());
        }
    }
}
