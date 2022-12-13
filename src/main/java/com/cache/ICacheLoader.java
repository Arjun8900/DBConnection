package com.cache;

public interface ICacheLoader<K, V> {
    V load(K var1) throws Exception;
}
