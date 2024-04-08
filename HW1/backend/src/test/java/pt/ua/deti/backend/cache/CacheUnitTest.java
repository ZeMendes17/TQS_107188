package pt.ua.deti.backend.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

class CacheUnitTest {
    private CacheItem<Double> dollar;
    private CacheItem<Double> real;
    private InMemoryCache<String, Double> cache;

    @BeforeEach
    public void setUp() {
        dollar = new CacheItem<>(1.0835, 5);
        real = new CacheItem<>(5.4765, 5);
        cache = new InMemoryCache<>();
    }

    @AfterEach
    public void tearDown() {
        cache.evictAll();

        dollar = null;
        real = null;
        cache = null;
    }

    @Test
    @DisplayName("Test if containsKey returns true if the key is in the cache")
    void containsKey_returnsTrueIfKeyInCache() {
        cache.put("dollar", dollar.getValue(), dollar.getTtl());
        assertEquals(true, cache.containsKey("dollar"));

        // if it was not cached, it should return false
        assertEquals(false, cache.containsKey("real"));
    }

    @Test
    @DisplayName("Test if get returns the correct value if the item is cached")
    void get_returnsValueIfCached() {
        cache.put("dollar", dollar.getValue(), dollar.getTtl());
        assertEquals(dollar.getValue(), cache.get("dollar"));

        // if it was not cached, it should return null
        assertEquals(null, cache.get("real"));
    }

    @Test
    @DisplayName("Test if evict removes the item from the cache")
    void evict_removesItemFromCache() {
        cache.put("dollar", dollar.getValue(), dollar.getTtl());
        cache.put("real", real.getValue(), real.getTtl());

        cache.evict("dollar");
        assertEquals(null, cache.get("dollar"));

        // it should not remove other items
        assertEquals(real.getValue(), cache.get("real"));
    }

    @Test
    @DisplayName("Test if evictAll removes all items from the cache")
    void evictAll_removesAllItemsFromCache() {
        cache.put("dollar", dollar.getValue());
        cache.put("real", real.getValue());

        cache.evictAll();
        assertEquals(null, cache.get("dollar"));
        assertEquals(null, cache.get("real"));
    }

    @Test
    @DisplayName("Test if getKeys returns a list with all keys in the cache")
    void getKeys_returnsListWithAllKeys() {
        cache.put("dollar", dollar.getValue());
        cache.put("real", real.getValue());

        assertEquals(2, cache.getKeys().size());
        assertEquals(true, cache.getKeys().contains("dollar"));
        assertEquals(true, cache.getKeys().contains("real"));
    }

    @Test
        @DisplayName("Test if the cache is evicted after the ttl")
        void cacheIsEvictedAfterTtl() {
            // Put a value into the cache with a TTL of 2 seconds
            cache.put("dollar", dollar.getValue(), 2);

            // have to use this, or it gives a code smell
            await().atMost(5, SECONDS) // Maximum wait time (in seconds)
                .until(() -> cache.get("dollar") == null);

            // Assert that the cache entry has been evicted
            assertNull(cache.get("dollar"));
        }
}
