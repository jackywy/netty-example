package com.wy.gps.distance.domain;

/**
 * 度分秒的经纬度位置
 */
public class RecommendPositionDMS {
    private RecommendDMS lng;//经度
    private RecommendDMS lat;//纬度


    public RecommendPositionDMS(RecommendDMS lng, RecommendDMS lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public RecommendDMS getLng() {
        return lng;
    }

    public void setLng(RecommendDMS lng) {
        this.lng = lng;
    }

    public RecommendDMS getLat() {
        return lat;
    }

    public void setLat(RecommendDMS lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendPositionDMS that = (RecommendPositionDMS) o;

        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        return lat != null ? lat.equals(that.lat) : that.lat == null;
    }

    @Override
    public int hashCode() {
        int result = lng != null ? lng.hashCode() : 0;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        return result;
    }

    public static RecommendPositionDMS covertToRecommendPositionDMS(PositionDMS positionDMS, int lngInterval, int latInterval) {
        if(positionDMS!=null){

        }
        return null;
    }

    @Override
    public String toString() {
        return "RecommendPositionDMS{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
