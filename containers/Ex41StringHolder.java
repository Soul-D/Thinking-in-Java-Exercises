package containers;

import java.util.*;

public class Ex41StringHolder implements Comparable<Ex41StringHolder> {
    public String first;
    public String second;

    public Ex41StringHolder(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Ex41StringHolder o) {
        return first.compareTo(o.first);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(first).append(' ');
        sb.append(second);
        return sb.toString();
    }

    public static Comparator<Ex41StringHolder> getComparator(){
        return new Comparator<Ex41StringHolder>() {
            public int compare(Ex41StringHolder o1, Ex41StringHolder o2) {
                return o1.second.compareTo(o2.second);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ex41StringHolder that = (Ex41StringHolder) o;

        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        return second != null ? second.equals(that.second) : that.second == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}

class Ex41Test{
    public static void main(String[] args) {
        Set<Ex41StringHolder> set = new HashSet<Ex41StringHolder>();
        set.add(new Ex41StringHolder("1","2"));
        set.add(new Ex41StringHolder("1","2"));
        System.out.println(set);
        Map<Ex41StringHolder,Integer> map = new HashMap<Ex41StringHolder,Integer>();
        map.put(new Ex41StringHolder("1","2"),1);
        map.put(new Ex41StringHolder("1","2"),2);
        System.out.println(map);
    }
}

