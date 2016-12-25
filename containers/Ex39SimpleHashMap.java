package containers;

import net.mindview.util.Countries;

import java.util.*;

public class Ex39SimpleHashMap<K, V> extends AbstractMap<K, V> {

    private int capacity = 16;
    private int size = 0;
    private double loadFactor = 0.75;

    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[capacity];

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % capacity;
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
        if (!found) {
            buckets[index].add(pair);
            size++;
        }
        if (((double)size)/capacity >= loadFactor){
            rehash();
        }
        return oldValue;
    }

    private boolean isPrime(int n) {
        if (n%2==0) return false;
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % capacity;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
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

    private void rehash(){
        int newCapacity = capacity * 2;
        while(!isPrime(newCapacity++));
        capacity = --newCapacity;
        Set<Entry<K, V>> entySet = entrySet();
        buckets = new LinkedList[capacity];
        for (Entry<K, V> entry : entySet) {
            put(entry.getKey(),entry.getValue());
        }
    }

    public static void main(String[] args) {
        Ex39SimpleHashMap<String, String> m = new Ex39SimpleHashMap<String, String>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ERITREA"));
        System.out.println(m.entrySet());
    }
}
