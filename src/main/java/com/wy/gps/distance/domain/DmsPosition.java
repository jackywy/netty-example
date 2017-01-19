package com.wy.gps.distance.domain;

import java.io.Serializable;

/**
 * 度分秒经纬度
 */
public class DmsPosition implements Serializable {
    private int degrees;//度
    private int minutes;//分
    private int seconds;//秒

    /**
     * 构造函数
     *
     * @param degrees
     * @param minutes
     * @param seconds
     */
    public DmsPosition(int degrees, int minutes, int seconds) {
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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmsPosition that = (DmsPosition) o;

        if (degrees != that.degrees) return false;
        if (minutes != that.minutes) return false;
        return seconds == that.seconds;
    }

    @Override
    public int hashCode() {
        int result = degrees;
        result = 31 * result + minutes;
        result = 31 * result + seconds;
        return result;
    }

    @Override
    public String toString() {
        return "DmsPosition{" +
                "degrees=" + degrees +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
