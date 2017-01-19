package com.wy.gps.distance.domain;

import java.io.Serializable;

/**
 * 度分秒经纬度
 */
public class DMS implements Serializable {
    private Integer degrees;//度
    private Integer minutes;//分
    private Double seconds;//秒

    /**
     * 构造函数
     *
     * @param degrees
     * @param minutes
     * @param seconds
     */
    public DMS(Integer degrees, Integer minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Double getSeconds() {
        return seconds;
    }

    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DMS dms = (DMS) o;

        if (degrees != null ? !degrees.equals(dms.degrees) : dms.degrees != null) return false;
        if (minutes != null ? !minutes.equals(dms.minutes) : dms.minutes != null) return false;
        return seconds != null ? seconds.equals(dms.seconds) : dms.seconds == null;
    }

    @Override
    public int hashCode() {
        int result = degrees != null ? degrees.hashCode() : 0;
        result = 31 * result + (minutes != null ? minutes.hashCode() : 0);
        result = 31 * result + (seconds != null ? seconds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DMS{" +
                "degrees=" + degrees +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
