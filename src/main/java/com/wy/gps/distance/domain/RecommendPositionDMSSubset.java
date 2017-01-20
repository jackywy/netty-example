package com.wy.gps.distance.domain;

import java.util.List;

/**
 * 推荐经纬度范围类位置点子集
 */
public class RecommendPositionDMSSubset {

    private Integer totalCount;
    private List<PositionDMS> positionDMSList;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<PositionDMS> getPositionDMSList() {
        return positionDMSList;
    }

    public void setPositionDMSList(List<PositionDMS> positionDMSList) {
        this.positionDMSList = positionDMSList;
    }

    public RecommendPositionDMSSubset() {
    }

    public RecommendPositionDMSSubset(Integer totalCount, List<PositionDMS> positionDMSList) {
        this.totalCount = totalCount;
        this.positionDMSList = positionDMSList;
    }

    @Override
    public String toString() {
        return "RecommendPositionDMSSubset{" +
                "totalCount=" + totalCount +
                ", positionDMSList=" + positionDMSList +
                '}';
    }
}
