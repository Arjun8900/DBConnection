package com.cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CaffeineLoadingCache<K, V> extends CaffeineCache<K, V> implements ILoadingCache<K, V>{

    protected CaffeineLoadingCache(LoadingCache<K, V> cache) {
        super(cache);
    }

    public CaffeineLoadingCache(ICacheLoader<K, V> loader, long size, long time){
        this(Caffeine
                .newBuilder()
                .maximumSize(size)
                .expireAfterWrite(time, TimeUnit.MINUTES)
                .recordStats()
                .build(loader::load));
    }

    @Override
    public V get(K key) throws Exception {
        return ((LoadingCache<K, V>)cache).get(key);
    }

    @Override
    public void refresh(K key) throws Exception {
        ((LoadingCache<K, V>)cache).refresh(key);
    }
}
