package containers;

import net.mindview.util.Countries;

import java.util.*;

public class Ex23SimpleHashMap<K, V> implements Map<K, V> {
    static final int SIZE = 997;

    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets =
            new LinkedList[SIZE];

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[SIZE];
    }

    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        Iterator<MapEntry<K, V>> iterator = buckets[index].iterator();
        while (iterator.hasNext()){
            MapEntry<K, V> pair = iterator.next();
            if (pair.getKey().equals(key)){
                V previous = pair.getValue();
                iterator.remove();
                return previous;
            }
        }
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }

    public int size() {
        return entrySet().size();
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public boolean containsKey(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        LinkedList<MapEntry<K, V>> list = buckets[index];
        if (list == null)
            return false;
        for (MapEntry<K, V> pair : list) {
            if (pair.getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (Entry<K, V> pair : entrySet()){
            if (pair.getValue().equals(value))
                return true;
        }
        return false;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> pair : m.entrySet()){
            put(pair.getKey(),pair.getValue());
        }
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (Map.Entry<K, V> pair : entrySet()){
            set.add(pair.getKey());
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> list = new ArrayList<V>();
        for (Map.Entry<K, V> pair : entrySet()){
            list.add(pair.getValue());
        }
        return list;
    }

    public static void main(String[] args) {
        Ex23SimpleHashMap<String, String> m = new Ex23SimpleHashMap<String, String>();
        m.putAll(Countries.capitals(25));
        System.out.println(m.size());
        System.out.println(m.keySet());
        System.out.println(m.values());
        System.out.println(m.containsValue("Asmara"));
    }
}
