package com.dbmananger;

import com.cache.CacheLibrary;
import com.cache.ILoadingCache;
import com.data.DBDetail;

import java.util.List;

public class CacheManager {
    private static final CacheLibrary cacheLibrary = CacheLibrary.CAFFEINE;
    private static final int responseCacheLimit = 10;
    private static final int responseCacheExpirationMinutes = 15;
    private static final OracleDBManager oracleDBManager = new OracleDBManager();
    private static final AWSOracleDBManager awsdbManager = new AWSOracleDBManager();

    private final ILoadingCache<String, List<String>> cache = cacheLibrary.getCache(this::manager, responseCacheLimit, responseCacheExpirationMinutes);

    // Actual manager caller

    /**
     *
     * @param dbName
     * @return String
     * @throws Exception
     */
    public List<String> generateOrgMonitoringResponse(DBDetail dbName) throws Exception {
//        cache.delete(dbName);
        List<String> res = null;
        try {
            res = cache.get(dbName.getDbName());
            System.out.println(cache.getStats());
        }catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
    public List<String> manager(String dbName) {
        System.out.println(dbName);
        return awsdbManager.getTables(dbName);
    }
}
