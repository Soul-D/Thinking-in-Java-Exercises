package containers;

import net.mindview.util.Countries;

import java.util.*;

public class Ex25SimpleHashMap<K, V> extends AbstractMap<K, V> {
    static final int SIZE = 997;

    class MapEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        private MapEntry<K, V> next;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V v) {
            V result = value;
            value = v;
            return result;
        }

        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        public boolean equals(Object o) {
            if (!(o instanceof containers.MapEntry)) return false;
            containers.MapEntry me = (containers.MapEntry) o;
            return
                    (key == null ?
                            me.getKey() == null : key.equals(me.getKey())) &&
                            (value == null ?
                                    me.getValue() == null : value.equals(me.getValue()));
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    @SuppressWarnings("unchecked")
    MapEntry<K, V>[] buckets = new MapEntry[SIZE];

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new MapEntry<K, V>(key, value);
        else {
            MapEntry<K, V> bucket = buckets[index];
            if (bucket.getKey().equals(key)) {
                oldValue = bucket.getValue();
                bucket.setValue(value);
                return oldValue;
            }
            while (bucket.next != null) {
                bucket = bucket.next;
                if (bucket.getKey().equals(key)) {
                    oldValue = bucket.getValue();
                    bucket.setValue(value);
                    return oldValue;
                }
            }
            bucket.next = new MapEntry<K, V>(key, value);
        }
        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntry<K, V> bucket = buckets[index];
        while (bucket != null)
        {
            if (bucket.getKey().equals(key))
                return bucket.getValue();
            bucket = bucket.next;
        }
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (MapEntry<K, V> bucket : buckets) {
            while (bucket != null)
            {
                set.add(bucket);
                bucket = bucket.next;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        Ex25SimpleHashMap<String, String> m =
                new Ex25SimpleHashMap<String, String>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ERITREA"));
        System.out.println(m.entrySet());
    }
}
