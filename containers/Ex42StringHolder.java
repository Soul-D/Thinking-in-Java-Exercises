package containers;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.*;

public class Ex42StringHolder implements Comparable<Ex42StringHolder> {
    public String first;
    public String second;

    public Ex42StringHolder(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Ex42StringHolder o) {
        int res = first.compareToIgnoreCase(o.first);
        if (res != 0)
            return res;
        return second.compareToIgnoreCase(o.second);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(first).append(' ');
        sb.append(second);
        return sb.toString();
    }
}

class Ex42Test{
    public static List<Ex42StringHolder> getFilledList(int size){
        List<Ex42StringHolder> result = new ArrayList<Ex42StringHolder>();
        Generator<String> gen = new RandomGenerator.String();
        for (int i = 0; i < size; i++){
            result.add(new Ex42StringHolder(gen.next(),gen.next()));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Ex42StringHolder> list = getFilledList(13);
        Ex42StringHolder[] array = getFilledList(13).toArray(new Ex42StringHolder[0]);
        System.out.println(list);
        System.out.println(Arrays.toString(array));
        System.out.println();
        Collections.sort(list);
        Arrays.sort(array);
        System.out.println(list);
        System.out.println(Arrays.toString(array));
    }
}

