package com.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineCache<K, V> implements ICache<K, V> {
    protected final Cache<K, V> cache;

    protected CaffeineCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    public CaffeineCache(long size, long time){

        this.cache = Caffeine
                .newBuilder()
                .maximumSize(size)
                .expireAfterWrite(time, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    @Override
    public V get(K key) throws Exception {
        return cache.getIfPresent(key);
    }

    @Override
    public void put(K key, V value) throws Exception {
        cache.put(key, value);
    }

    @Override
    public void delete(K key) throws Exception {
        cache.invalidate(key);
    }

    @Override
    public CacheStats getStats() throws Exception {
        com.github.benmanes.caffeine.cache.stats.CacheStats stats = cache.stats();
        return new CacheStats(stats.hitCount(),
                stats.missCount(),
                stats.loadSuccessCount(),
                stats.loadFailureCount(),
                stats.totalLoadTime(),
                stats.evictionCount());
    }
}
