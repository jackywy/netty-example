package com.wy;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 开启线程池计算从1到260万数据的累加
 */
public class TestCachedThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //获取内核数
        int processorNumber = Runtime.getRuntime().availableProcessors();
        //创建线程池
//        ExecutorService executor = new ThreadPoolExecutor(processorNumber, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ExecutorService executor = new ThreadPoolExecutor(processorNumber, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ArrayList<Future<Long>> resultList = new ArrayList<Future<Long>>();
        long startTime = System.currentTimeMillis();
        //创建并提交任务
        AddNumberTask task = new AddNumberTask(1L, 260 * 100 * 100L);
        Future<Long> future = executor.submit(task);
        resultList.add(future);
        executor.shutdown();
        long total = 0;
        for (Future<Long> loopFuture : resultList) {
            while (true) {
                if (loopFuture.isDone() && !loopFuture.isCancelled()) {
                    total = loopFuture.get();
                    break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "毫秒");
        System.out.println("total sum is " + total);
    }
}

