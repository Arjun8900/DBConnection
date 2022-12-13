package com.cache;

public interface ICache<K, V> {
    /**
     * Get the value for a CacheKey
     * @throws Exception exception
     */
    V get(K key) throws Exception;

    /**
     * Add entry into cache
     * @throws Exception exception
     */
    void put(K key, V value) throws Exception;

    /**
     * Delete key and its corresponding value
     * @throws Exception exception
     */
    void delete(K key) throws Exception;

    /**
     * Get the cache stats object
     */
    CacheStats getStats() throws Exception;
}
