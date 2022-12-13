package com.cache;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class CacheStats {
    private final long hitCount;
    private final long missCount;
    private final long loadSuccessCount;
    private final long loadExceptionCount;
    private final long totalLoadTime;
    private final long evictionCount;

    public CacheStats(long hitCount, long missCount, long loadSuccessCount, long loadExceptionCount, long totalLoadTime, long evictionCount) {
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.loadSuccessCount = loadSuccessCount;
        this.loadExceptionCount = loadExceptionCount;
        this.totalLoadTime = totalLoadTime;
        this.evictionCount = evictionCount;
    }

    private long requestCount() {
        return this.hitCount + this.missCount;
    }

    public double hitRate() {
        long requestCount = this.requestCount();
        return requestCount == 0L ? 1.0D : (double)this.hitCount / (double)requestCount;
    }

    public double missRate() {
        long requestCount = this.requestCount();
        return requestCount == 0L ? 0.0D : (double)this.missCount / (double)requestCount;
    }

    private long loadCount() {
        return this.loadSuccessCount + this.loadExceptionCount;
    }

    public double loadExceptionRate() {
        long totalLoadCount = this.loadCount();
        return totalLoadCount == 0L ? 0.0D : (double)this.loadExceptionCount / (double)totalLoadCount;
    }

    public double averageLoadPenalty() {
        long totalLoadCount = this.loadCount();
        return totalLoadCount == 0L ? 0.0D : (double)this.totalLoadTime / (double)totalLoadCount;
    }
}
