package com.wy.gps.distance.domain;

import java.io.Serializable;

/**
 * 度分秒经纬度
 */
public class RecommendDMS implements Serializable {
    private Integer degrees;//度
    private Integer minMinutes;
    private Integer maxMinutes;

    public RecommendDMS() {
    }

    public RecommendDMS(Integer degrees, Integer minMinutes, Integer maxMinutes) {
        this.degrees = degrees;
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendDMS that = (RecommendDMS) o;

        if (degrees != null ? !degrees.equals(that.degrees) : that.degrees != null) return false;
        if (minMinutes != null ? !minMinutes.equals(that.minMinutes) : that.minMinutes != null) return false;
        return maxMinutes != null ? maxMinutes.equals(that.maxMinutes) : that.maxMinutes == null;
    }

    @Override
    public int hashCode() {
        int result = degrees != null ? degrees.hashCode() : 0;
        result = 31 * result + (minMinutes != null ? minMinutes.hashCode() : 0);
        result = 31 * result + (maxMinutes != null ? maxMinutes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecommendDMS{" +
                "degrees=" + degrees +
                ", minMinutes=" + minMinutes +
                ", maxMinutes=" + maxMinutes +
                '}';
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
        return recommendDMS;
    }
}
