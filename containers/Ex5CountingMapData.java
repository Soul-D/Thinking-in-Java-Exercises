package containers;

import java.util.*;

public class Ex5CountingMapData extends AbstractMap<Integer, String> {
    private int size;
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public Ex5CountingMapData(int size) {
        if (size < 0) this.size = 0;
        this.size = size;
    }

    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }

        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }

        public Integer getKey() {
            return index;
        }

        public String getValue() {
            return chars[index % chars.length] + Integer.toString(index / chars.length);
        }

        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    public Set<Map.Entry<Integer, String>> entrySet() {
        return new EntrySet();
    }

    class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
        private class Iter implements Iterator<Map.Entry<Integer, String>> {
            Entry entry = new Entry(-1);
            public boolean hasNext() {
                return entry.index < size - 1;
            }

            public Map.Entry<Integer, String> next() {
                entry.index++;
                return entry;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public Iterator<Map.Entry<Integer, String>> iterator() {
            return new Iter();
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex5CountingMapData(60));
    }
}