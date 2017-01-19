package com.wy.gps.distance.domain;

import java.io.Serializable;

/**
 * 度分秒经纬度
 */
public class DMS implements Serializable {
    private int degrees;//度
    private int minutes;//分
    private double seconds;//秒

    /**
     * 构造函数
     *
     * @param degrees
     * @param minutes
     * @param seconds
     */
    public DMS(int degrees, int minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DMS dms = (DMS) o;

        if (degrees != dms.degrees) return false;
        if (minutes != dms.minutes) return false;
        return Double.compare(dms.seconds, seconds) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = degrees;
        result = 31 * result + minutes;
        temp = Double.doubleToLongBits(seconds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
