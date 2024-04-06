package pt.ua.deti.backend.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache<K, V> {

    private final Map<K, CacheItem<V>> cache;
    private static final int defaultTtl = 60;

    // Constructor
    public InMemoryCache() {
        this.cache = new ConcurrentHashMap<>();
        new Thread(new CacheEvict()).start();
    }

    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if (item == null || item.isExpired()) {
            return null;
        }
        return item.getValue();
    }

    public void put(K key, V value) {
        cache.put(key, new CacheItem<>(value, defaultTtl));
    }

    public void put(K key, V value, int ttl) {
        cache.put(key, new CacheItem<>(value, ttl));
    }

    public void evict(K key) {
        cache.remove(key);
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    private class CacheEvict implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // evict cache
                for (Map.Entry<K, CacheItem<V>> entry : cache.entrySet()) {
                    if (entry.getValue().isExpired()) {
                        cache.remove(entry.getKey());
                    }
                }
            }
        }
    }
}
