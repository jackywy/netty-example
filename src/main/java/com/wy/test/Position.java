package com.wy.test;

/**
 * Created by wangyang on 2017/1/18.
 */
public class Position {
    private Double lng;//经度
    private Double lat;//维度

    public Position(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (lng != null ? !lng.equals(position.lng) : position.lng != null) return false;
        return lat != null ? lat.equals(position.lat) : position.lat == null;
    }

    @Override
    public int hashCode() {
        int result = lng != null ? lng.hashCode() : 0;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
