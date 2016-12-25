package containers;

import net.mindview.util.CountingMapData;

import java.util.*;

public class Ex17SlowMap<K, V> implements Map<K, V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    public V remove(Object key) {
        V oldValue = get(key);
        if (keys.contains(key))
        {
            values.remove(keys.indexOf(key));
            keys.remove(key);
        }
        return oldValue;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K,? extends V> pair : m.entrySet()){
            put(pair.getKey(),pair.getValue());
        }
    }

    public void clear() {
        keys.clear();
        values.clear();
    }

    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return keys.iterator();
            }

            @Override
            public int size() {
                return keys.size();
            }
        };
    }

    public Collection<V> values() {
        return values;
    }

    public int size() {
        return keys.size();
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }

    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    public Set<Entry<K, V>> entrySet() {
        final Ex17SlowMap<K, V> thisMap = this;
        return new AbstractSet<Entry<K, V>>() {
            Ex17SlowMap<K, V> map = thisMap;

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    int index = -1;
                    boolean nextCalled = false;

                    public boolean hasNext() {
                        return index+1<map.size();
                    }

                    public Entry<K, V> next() {
                        nextCalled = true;
                        index++;
                        return new Entry<K, V>() {
                            public K getKey() {
                                return map.keys.get(index);
                            }

                            public V getValue() {
                                return map.values.get(index);
                            }

                            public V setValue(V value) {
                                V result = getValue();
                                return map.values.set(index,value);
                            }
                        };
                    }

                    public void remove() {
                        if (!nextCalled)
                            throw new IllegalStateException();
                        nextCalled = false;
                        map.keys.remove(index);
                        map.values.remove(index);
                        index--;
                    }
                };
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }

    public static void main(String[] args) {
        Ex17SlowMap<Integer, String> map = new Ex17SlowMap<Integer, String>();
        map.putAll(new CountingMapData(25));
        System.out.println(map.keySet());
    }
}
