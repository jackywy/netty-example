package com.wy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 开启线程池计算260万条经纬度对之间的距离
 * 耗时：4363毫秒
 * map大小：2600000
 */
public class DistanceCounterThreadPool {
    //gps记录数
    private static final int count = 260 * 10000;

    //获取内核数
    private static final int processorNumber = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Map<Position[], Double> distanceMap = getPositionDistanceMap(getPositionsList());
        System.out.println("map大小：" + distanceMap.size());

    }


    /**
     * 获取初始化数据
     *
     * @return
     */
    private static List<Position[]> getPositionsList() {
        //定义经纬度数据对List
        List<Position[]> positionList = new ArrayList<Position[]>(count);
        //初始化测试数据
        for (int i = 0; i < count; i++) {
            Position[] positions = new Position[2];
            positions[0] = new Position(117.539905, 36.719823);
            positions[1] = new Position(119.51626, 25.962553);
            positionList.add(positions);
        }
        return positionList;
    }

    /**
     * 获得两两经纬度之间的距离
     *
     * @param positionList
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static Map<Position[], Double> getPositionDistanceMap(List<Position[]> positionList) throws InterruptedException, ExecutionException {
        //创建线程池
        ExecutorService executor = new ThreadPoolExecutor(processorNumber, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ArrayList<Future<Map<Position[], Double>>> resultList = new ArrayList<Future<Map<Position[], Double>>>();
        //获得计算开始时间
        long startTime = System.currentTimeMillis();
        //创建并提交任务
        DistanceCounterTask task = new DistanceCounterTask(positionList);
        Future<Map<Position[], Double>> future = executor.submit(task);
        resultList.add(future);
        executor.shutdown();
        Map<Position[], Double> distanceMap = new ConcurrentHashMap<Position[], Double>(count);
        for (Future<Map<Position[], Double>> loopFuture : resultList) {
            while (true) {
                if (loopFuture.isDone() && !loopFuture.isCancelled()) {
                    distanceMap = loopFuture.get();
                    break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "毫秒");
        return distanceMap;
    }
}

