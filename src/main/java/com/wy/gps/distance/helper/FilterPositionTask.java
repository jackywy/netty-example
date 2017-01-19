package com.wy.gps.distance.helper;

import com.wy.Position;
import com.wy.gps.distance.domain.PositionDMS;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 得到一组相似位置的map
 */

public class FilterPositionTask implements Callable<Map<PositionDMS,Integer>> {
    //经度相差一分
    private static final int LNG_INTERVAL_VALUE = 10;
    //维度相差一分
    private static final int LAT_INTERVAL_VALUE = 10;
    private Set<PositionDMS> positionDMSList;

    public FilterPositionTask(Set<PositionDMS> positionDMSList) {
        this.positionDMSList = positionDMSList;
    }

    @Override
    public Map<PositionDMS,Integer> call() throws Exception {
        Map<PositionDMS,Integer> filterPositionDMSMap = new HashMap<PositionDMS, Integer>();
        for (PositionDMS positionDMS : positionDMSList) {

        }
        return filterPositionDMSMap;
    }

    /**
     * 根据指定条件过滤掉该位置数据
     *
     * @param existPositionDMS
     * @param positionDMS
     * @return
     */
    public static boolean isFilterOut(PositionDMS existPositionDMS, PositionDMS positionDMS) {
        boolean filterFlag = true;
        boolean a = Math.abs(existPositionDMS.getLng().getMinutes() - positionDMS.getLng().getMinutes()) > LNG_INTERVAL_VALUE;
        System.out.println("参照纬度分:" + existPositionDMS.getLat().getMinutes() + "比较纬度分" + positionDMS.getLat().getMinutes());
        System.out.println("参照位置与比较位置纬度分差:" + Math.abs(existPositionDMS.getLat().getMinutes() - positionDMS.getLat().getMinutes()));
        boolean b = Math.abs(existPositionDMS.getLat().getMinutes() - positionDMS.getLat().getMinutes()) > LAT_INTERVAL_VALUE;
        if (a || b) {
            filterFlag = false;
        }
        return filterFlag;
    }


}
