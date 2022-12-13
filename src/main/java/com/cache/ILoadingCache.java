package com.cache;

public interface ILoadingCache<K, V> extends ICache<K, V>{
    /**
     * Refresh the cache
     * @throws Exception exception
     */
    void refresh(K key) throws Exception;

}
