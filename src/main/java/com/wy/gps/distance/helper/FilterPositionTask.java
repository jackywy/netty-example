package com.wy.gps.distance.helper;

import com.wy.gps.distance.domain.PositionDMS;
import com.wy.gps.distance.domain.RecommendPositionDMS;
import com.wy.gps.distance.domain.RecommendPositionDMSSubset;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * 得到一组相似位置的map
 */

public class FilterPositionTask implements Callable<Map<RecommendPositionDMS, RecommendPositionDMSSubset>> {
    //经度相差一分
    private static final int LNG_INTERVAL_VALUE = 1;
    //维度相差一分
    private static final int LAT_INTERVAL_VALUE = 1;
    //满足分组位置范围的个数
    private static final int MIN_SUBSET_COUNT = 1;
    private List<PositionDMS> positionDMSList;

    public FilterPositionTask(List<PositionDMS> positionDMSList) {
        this.positionDMSList = positionDMSList;
    }

    @Override
    public Map<RecommendPositionDMS, RecommendPositionDMSSubset> call() throws Exception {
        Map<RecommendPositionDMS, RecommendPositionDMSSubset> filterPositionDMSMap = new HashMap<RecommendPositionDMS, RecommendPositionDMSSubset>();
        for (PositionDMS positionDMS : positionDMSList) {
            RecommendPositionDMS recommendPositionDMS = RecommendPositionDMS.covertToRecommendPositionDMS(positionDMS, LNG_INTERVAL_VALUE, LAT_INTERVAL_VALUE);
            if (filterPositionDMSMap.containsKey(recommendPositionDMS)) {
                RecommendPositionDMSSubset recommendPositionDMSSubset = filterPositionDMSMap.get(recommendPositionDMS);
                recommendPositionDMSSubset.setTotalCount(recommendPositionDMSSubset.getTotalCount() + 1);
                recommendPositionDMSSubset.getPositionDMSList().add(positionDMS);
                filterPositionDMSMap.put(recommendPositionDMS, recommendPositionDMSSubset);
            } else {
                List<PositionDMS> positionDMSList = new ArrayList<PositionDMS>(1);
                positionDMSList.add(positionDMS);
                filterPositionDMSMap.put(recommendPositionDMS, new RecommendPositionDMSSubset(1, positionDMSList));
            }
        }
        Iterator<RecommendPositionDMS> iterator = filterPositionDMSMap.keySet().iterator();
        while (iterator.hasNext()) {
            RecommendPositionDMS recommendPositionDMS = iterator.next();
            RecommendPositionDMSSubset recommendPositionDMSSubset = filterPositionDMSMap.get(recommendPositionDMS);
            if (recommendPositionDMSSubset.getTotalCount() < MIN_SUBSET_COUNT) {
                iterator.remove();
            }
        }
        return filterPositionDMSMap;
    }


}
