package com.wy.gps.distance.helper;

import com.wy.gps.distance.data.PositionsDataUtil;
import com.wy.gps.distance.domain.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

import static java.math.BigDecimal.ROUND_DOWN;
import static java.math.BigDecimal.ROUND_UP;

/**
 * 位置工具类
 */
public class PositionUtils {
    //gps记录数
    private static final int count = 260 * 10000;
    //获取内核数
    private static final int processorNumber = Runtime.getRuntime().availableProcessors();

    /**
     * 度(DDD)转换成度分秒(DMS)
     *
     * @param ddd
     * @return
     */
    public static DMS covertDDDToDMS(Double ddd) {
        //获取度的小数部分
        BigDecimal degreesDecimal = BigDecimal.valueOf(ddd).subtract(BigDecimal.valueOf(ddd.intValue()));
        //获取带小数位的分
        BigDecimal minute = degreesDecimal.multiply(new BigDecimal("60"));
        //获取分的小数部分
        BigDecimal minuteDecimal = minute.subtract(BigDecimal.valueOf(minute.intValue()));
        //获取带小数位的秒
        BigDecimal second = minuteDecimal.multiply(new BigDecimal("60"));
        return new DMS(ddd.intValue(), minute.intValue(), second.doubleValue());
    }

    /**
     * l
     * 度分秒(DMS)转换成度(DDD)
     *
     * @param dms
     * @return
     */
    public static Double covertDMSToDDD(DMS dms) {
        BigDecimal a = BigDecimal.valueOf(dms.getMinutes());
        BigDecimal aa = a.divide(new BigDecimal("60"), 6, ROUND_DOWN);
        BigDecimal b = BigDecimal.valueOf(dms.getSeconds());
        BigDecimal bb = b.divide(new BigDecimal("3600"), 6, ROUND_UP);
        return BigDecimal.valueOf(dms.getDegrees()).add(aa).add(bb).doubleValue();
    }

    /**
     * 获取一组相似经纬度位置列表
     *
     * @param positionDMSList
     * @return
     */
    public static Map<RecommendPositionDMS, RecommendPositionDMSSubset> getFilterDMSPositionMap(List<PositionDMS> positionDMSList) throws InterruptedException, ExecutionException {
        //创建线程池
        ExecutorService executor = new ThreadPoolExecutor(processorNumber, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ArrayList<Future<Map<RecommendPositionDMS, RecommendPositionDMSSubset>>> resultList = new ArrayList<Future<Map<RecommendPositionDMS, RecommendPositionDMSSubset>>>();
        //获得计算开始时间
        long startTime = System.currentTimeMillis();
        System.out.println("positionDMSList size:" + positionDMSList.size());
        //创建并提交任务
        FilterPositionTask task = new FilterPositionTask(positionDMSList);
        Future<Map<RecommendPositionDMS, RecommendPositionDMSSubset>> future = executor.submit(task);
        resultList.add(future);
        executor.shutdown();
        Map<RecommendPositionDMS, RecommendPositionDMSSubset> filterPositionDMSMap = new HashMap<RecommendPositionDMS, RecommendPositionDMSSubset>();
        for (Future<Map<RecommendPositionDMS, RecommendPositionDMSSubset>> loopFuture : resultList) {
            while (true) {
                if (loopFuture.isDone() && !loopFuture.isCancelled()) {
                    filterPositionDMSMap = loopFuture.get();
                    break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "毫秒");
        System.out.println("filterPositionDMSMap size:" + filterPositionDMSMap.size());
        Iterator<RecommendPositionDMS> iterator = filterPositionDMSMap.keySet().iterator();
        while (iterator.hasNext()) {
            RecommendPositionDMS recommendPositionDMS = iterator.next();
            RecommendPositionDMSSubset recommendPositionDMSSubset = filterPositionDMSMap.get(recommendPositionDMS);
            System.out.println("推荐常规位置地点坐标:"+recommendPositionDMS);
            System.out.println("该坐标被推荐为常规位置地点坐标的数据集个数:"+recommendPositionDMSSubset.getTotalCount());
        }
        return filterPositionDMSMap;
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //度转转换度分秒
        final Double lngDDD0 = 117.539905;//ddd经度
        final Double latDDD0 = 36.719823;//ddd维度
        final Double lngDDD1 = 117.539905;//ddd经度
        final Double latDDD1 = 36.819823;//ddd维度
        final Double lngDDD2 = 117.539905;//ddd经度
        final Double latDDD2 = 36.919823;//ddd维度
        System.out.println(PositionUtils.covertDDDToDMS(lngDDD0));
        System.out.println(PositionUtils.covertDDDToDMS(latDDD0));
        System.out.println(PositionUtils.covertDDDToDMS(lngDDD1));
        System.out.println(PositionUtils.covertDDDToDMS(latDDD1));
        System.out.println(PositionUtils.covertDDDToDMS(lngDDD2));
        System.out.println(PositionUtils.covertDDDToDMS(latDDD2));

        //度分秒转换度
        System.out.println(PositionUtils.covertDMSToDDD(new DMS(117, 32, 23.658)));
        System.out.println(PositionUtils.covertDMSToDDD(new DMS(36, 44, 11.3628)));

        //获取过滤的位置列表
        System.out.println("---开始准备数据---");
        List<PositionDMS> positionDMSList = new ArrayList<PositionDMS>();
        //测试数据
        positionDMSList = PositionsDataUtil.getPositionDMSDataFromFile();
        //随机数据
/*        for (int i = 0; i < 260 * 10000; i++) {
            Random random = new Random();
            Date randomDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND, -random.nextInt(89));
            randomDate = calendar.getTime();

            //得到带有gps时间的randomDate
            Double randomLat = random.nextInt(89) + Double.valueOf(random.nextDouble());
            positionDMSList.add(new PositionDMS(covertDDDToDMS(117.539905), covertDDDToDMS(randomLat), randomDate.getTime()));
        }*/
//        positionDMSList.add(new PositionDMS(covertDDDToDMS(117.539905), covertDDDToDMS(36.719823)));
//        positionDMSList.add(new PositionDMS(covertDDDToDMS(117.539905), covertDDDToDMS(36.619823)));
        System.out.println("---结束准备数据---");
        System.out.println("分组子集小于"+FilterPositionTask.MIN_SUBSET_COUNT+"条结果将被过滤");
        Map<RecommendPositionDMS, RecommendPositionDMSSubset> filterDMSPositionMap = getFilterDMSPositionMap(positionDMSList);
        System.out.println("被推荐位置个数为:" + filterDMSPositionMap.size());
    }
}
