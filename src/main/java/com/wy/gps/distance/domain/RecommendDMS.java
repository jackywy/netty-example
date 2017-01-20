package com.wy.gps.distance.domain;

import java.io.Serializable;

/**
 * 度分秒经纬度
 */
public class RecommendDMS implements Serializable {
    private Integer degrees;
    private Integer minMinutes;
    private Integer maxMinutes;
    private Integer minutes;

    public RecommendDMS() {
    }

    public RecommendDMS(Integer degrees, Integer minMinutes, Integer maxMinutes, Integer minutes) {
        this.degrees = degrees;
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
        this.minutes = minutes;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

    public Integer getMinMinutes() {
        return minMinutes;
    }

    public void setMinMinutes(Integer minMinutes) {
        this.minMinutes = minMinutes;
    }

    public Integer getMaxMinutes() {
        return maxMinutes;
    }

    public void setMaxMinutes(Integer maxMinutes) {
        this.maxMinutes = maxMinutes;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public static RecommendDMS convertRecommendDMS(DMS dms, int interval) {
        RecommendDMS recommendDMS = new RecommendDMS();
        if (dms != null) {
            recommendDMS.setDegrees(dms.getDegrees());
            if (dms.getMinutes() != null) {
                recommendDMS.setMinMinutes(dms.getMinutes() - interval);
                recommendDMS.setMaxMinutes(dms.getMinutes() + interval);
            }
        }
        recommendDMS.setMinutes(dms.getMinutes());
        return recommendDMS;
    }

    @Override
    public String toString() {
        return "RecommendDMS{" +
                "degrees=" + degrees +
                ", minMinutes=" + minMinutes +
                ", maxMinutes=" + maxMinutes +
                ", minutes=" + minutes +
                '}';
    }
}
