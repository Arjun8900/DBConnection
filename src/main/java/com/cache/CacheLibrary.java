package com.cache;


public enum CacheLibrary {
//    GUAVA {
//        @Override
//        public <K, V> ICache<K, V> getCache(long size, long time) {
//            return new GuavaCache<>(size, time);
//        }
//
//        @Override
//        public <K, V> ILoadingCache<K, V> getCache(ICacheLoader<K, V> loader, long size, long time) {
//            return new GuavaLoadingCache<>(loader, size, time);
//        }
//    },

    CAFFEINE {
        @Override
        public <K, V> ICache<K, V> getCache(long size, long time) {
            return new CaffeineCache<>(size, time);
        }

        @Override
        public <K, V> ILoadingCache<K, V> getCache(ICacheLoader<K, V> loader, long size, long time) {
            return new CaffeineLoadingCache<>(loader, size, time);
        }
    };

    public abstract <K, V> ICache<K, V> getCache(long size, long time);

    public abstract <K, V> ILoadingCache<K, V> getCache(ICacheLoader<K, V> loader, long size, long time);
}