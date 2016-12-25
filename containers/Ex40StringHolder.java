package containers;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.*;

public class Ex40StringHolder implements Comparable<Ex40StringHolder> {
    public String first;
    public String second;

    public Ex40StringHolder(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Ex40StringHolder o) {
        return first.compareTo(o.first);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(first).append(' ');
        sb.append(second);
        return sb.toString();
    }

    public static Comparator<Ex40StringHolder> getComparator(){
        return new Comparator<Ex40StringHolder>() {
            public int compare(Ex40StringHolder o1, Ex40StringHolder o2) {
                return o1.second.compareTo(o2.second);
            }
        };
    }
}

class Ex40Test{
    public static List<Ex40StringHolder> getFilledList(int size){
        List<Ex40StringHolder> result = new ArrayList<Ex40StringHolder>();
        Generator<String> gen = new RandomGenerator.String();
        for (int i = 0; i < size; i++){
            result.add(new Ex40StringHolder(gen.next(),gen.next()));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Ex40StringHolder> list = getFilledList(13);
        Ex40StringHolder[] array = getFilledList(13).toArray(new Ex40StringHolder[0]);
        Collections.sort(list);
        Arrays.sort(array);
        System.out.println(list);
        System.out.println(Arrays.toString(array));
        System.out.println();
        Collections.sort(list,Ex40StringHolder.getComparator());
        Arrays.sort(array,Ex40StringHolder.getComparator());
        System.out.println(list);
        System.out.println(Arrays.toString(array));
        int index = Collections.binarySearch(list,list.get(4),Ex40StringHolder.getComparator());
        System.out.println("index = " + index + ", element = " + list.get(index));
        index = Arrays.binarySearch(array,array[4],Ex40StringHolder.getComparator());
        System.out.println("index = " + index + ", element = " + array[index]);
    }
}
