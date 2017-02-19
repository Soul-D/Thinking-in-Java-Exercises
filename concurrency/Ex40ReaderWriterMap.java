package concurrency;

import net.mindview.util.CountingGenerator;
import net.mindview.util.MapData;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Ex40ReaderWriterMap<K, V> extends AbstractMap<K, V> {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private  Lock wlock = lock.writeLock();
    private  Lock rlock = lock.readLock();
    private Map<K,V> map;

    public Ex40ReaderWriterMap(Map<? extends K, ? extends V> m) {
        map = new HashMap<>(m);
    }

    @Override
    public V put(K key, V value) {
        wlock.lock();
        try {
            return map.put(key, value);
        } finally {
            wlock.unlock();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V get(Object key) {
        rlock.lock();
        try {
            return map.get(key);
        } finally {
            rlock.unlock();
        }
    }
}

class Ex40ReaderWriterMapTest extends MapTest {
    public Ex40ReaderWriterMapTest(int nReaders, int nWriters) {
        super("ReaderWriterMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> containerInitializer() {
        return new Ex40ReaderWriterMap<>(MapData.map(
                new CountingGenerator.Integer(),
                new CountingGenerator.Integer(), containerSize));
    }
}

class Ex40MapComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(5, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
        new Ex40ReaderWriterMapTest(10, 0);
        new Ex40ReaderWriterMapTest(9, 1);
        new Ex40ReaderWriterMapTest(5, 5);
        Tester.exec.shutdown();
    }
}

