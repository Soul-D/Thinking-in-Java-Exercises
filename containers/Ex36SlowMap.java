package containers;

import java.util.*;

import static containers.MapPerformance.tests;

public class Ex36SlowMap<K, V> extends AbstractMap<K, V> {
    private List<Map.Entry<K, V>> entryList = new ArrayList<Map.Entry<K, V>>();

    public V put(K key, V value) {
        for (Entry<K, V> entry : entryList) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        entryList.add(new MapEntry<K, V>(key, value));
        return null;
    }

    public V get(Object key) {
        for (Entry<K, V> entry : entryList) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return entryList.iterator();
            }

            @Override
            public int size() {
                return entryList.size();
            }
        };
    }
}

class Ex36SlowMap2<K, V> extends AbstractMap<K, V> {
    private List<Map.Entry<K, V>> entryList = new ArrayList<Map.Entry<K, V>>();

    public V put(K key, V value) {
        for (Entry<K, V> entry : entryList) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        entryList.add(new MapEntry<K, V>(key, value));
        Collections.sort(entryList, Ex36SlowMap2.<K, V>simpleComparator());
        return null;
    }

    private static <K, V> Comparator<Entry<K, V>> simpleComparator(){
        return new Comparator<Entry<K, V>>() {
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return ((Comparable<K>)o1.getKey()).compareTo(o2.getKey());
            }
        };
    }

    public V get(Object key) {
        int index = Collections.binarySearch(entryList,new MapEntry<K, V>((K)key,null), Ex36SlowMap2.<K, V>simpleComparator());
        if (index < 0)
            return null;
        else
            return entryList.get(index).getValue();
    }

    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return entryList.iterator();
            }

            @Override
            public int size() {
                return entryList.size();
            }
        };
    }
}

class Ex36Test{
    public static void main(String[] args) {
        Tester.defaultParams = TestParam.array(1000, 1000);
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new Ex17SlowMap<Integer, Integer>(), tests);
        Tester.run(new Ex36SlowMap<Integer, Integer>(), tests);
        Tester.run(new Ex36SlowMap2<Integer, Integer>(), tests);
    }
}