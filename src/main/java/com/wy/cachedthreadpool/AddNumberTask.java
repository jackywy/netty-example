package com.wy.cachedthreadpool;

import java.util.concurrent.Callable;

/**
 * Created by wangyang on 2017/1/19.
 */

public class AddNumberTask implements Callable<Long> {
    private long start;
    private long end;

    public AddNumberTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        long totalSum = 0;
        for (long i = start; i <= end; i++) {
            totalSum += i;
        }
        return totalSum;
    }

}
