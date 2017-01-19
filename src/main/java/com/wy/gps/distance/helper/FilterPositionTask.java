package com.wy.gps.distance.helper;

import com.wy.gps.distance.domain.PositionDMS;
import com.wy.gps.distance.domain.RecommendPositionDMS;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * 得到一组相似位置的map
 */

public class FilterPositionTask implements Callable<Map<RecommendPositionDMS, Integer>> {
    //经度相差一分
    private static final int LNG_INTERVAL_VALUE = 10;
    //维度相差一分
    private static final int LAT_INTERVAL_VALUE = 10;
    private Set<PositionDMS> positionDMSList;

    public FilterPositionTask(Set<PositionDMS> positionDMSList) {
        this.positionDMSList = positionDMSList;
    }

    @Override
    public Map<RecommendPositionDMS, Integer> call() throws Exception {
        Map<RecommendPositionDMS, Integer> filterPositionDMSMap = new HashMap<RecommendPositionDMS, Integer>();
        for (PositionDMS positionDMS : positionDMSList) {
            RecommendPositionDMS recommendPositionDMS = RecommendPositionDMS.covertToRecommendPositionDMS(positionDMS, LNG_INTERVAL_VALUE, LAT_INTERVAL_VALUE);
            if (filterPositionDMSMap.containsKey(recommendPositionDMS)) {
                filterPositionDMSMap.put(recommendPositionDMS, filterPositionDMSMap.get(recommendPositionDMS) + 1);
            } else {
                filterPositionDMSMap.put(recommendPositionDMS, 1);
            }
        }
        return filterPositionDMSMap;
    }


}
