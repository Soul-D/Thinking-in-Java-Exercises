package containers;

public class Ex8SList<E> {
    Link head = new Link();

    class Link {
        E data;
        Link next;
    }

    class SListlterator{
        Link current;
        Link previous;

        SListlterator(){
            current = head;
        }

        boolean hasNext(){
            return current.next != null;
        }

        E next(){
            previous = current;
            current = current.next;
            return current.data;
        }

        void add(E e){
            Link newLink = new Link();
            newLink.data = e;
            if (head == null)
                head = newLink;
            newLink.next=current.next;
            current.next=newLink;
        }

        void remove(){
            if (previous != null)
                previous.next = current.next;
            else
                throw new IllegalArgumentException();
        }
    }

    SListlterator iterator(){
        return new SListlterator();
    }

    @Override
    public String toString() {
        SListlterator iterator = new SListlterator();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            sb
                    .append(iterator.next())
                    .append(" ");
        }
        return sb.toString().trim();
    }
}

class Ex8Test{
    public static void main(String[] args) {
        Ex8SList<Integer> list = new Ex8SList<Integer>();
        Ex8SList.SListlterator iterator = list.iterator();
        for(int i = 0; i < 10; i++){
            iterator.add(i);
        }
        System.out.println(list);
        iterator = list.iterator();
        iterator.next();
        iterator.remove();
        System.out.println(list);
        iterator.next();
        iterator.next();
        iterator.remove();
        System.out.println(list);
    }
}
