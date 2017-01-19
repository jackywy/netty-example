package com.wy.gps.distance.domain;

/**
 * 度分秒的经纬度位置
 */
public class PositionDMS {
    private DMS lng;//经度
    private DMS lat;//纬度

    public PositionDMS(DMS lng, DMS lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public DMS getLng() {
        return lng;
    }

    public void setLng(DMS lng) {
        this.lng = lng;
    }

    public DMS getLat() {
        return lat;
    }

    public void setLat(DMS lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionDMS that = (PositionDMS) o;

        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        return lat != null ? lat.equals(that.lat) : that.lat == null;
    }

    @Override
    public int hashCode() {
        int result = lng != null ? lng.hashCode() : 0;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PositionDMS{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
