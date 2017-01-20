package com.wy.gps.distance.domain;

/**
 * 度分秒的经纬度位置
 */
public class RecommendPositionDMS {
    private RecommendDMS lng;//经度
    private RecommendDMS lat;//纬度

    public RecommendPositionDMS() {
    }

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
        if (lng != null && lat != null && that.lng != null && that.lat != null) {
            //两个位置经度和纬度的度不一样，则判断位置不相等
            if (!lng.getDegrees().equals(that.lng.getDegrees()) || !lat.getDegrees().equals(that.lat.getDegrees())) {
                return false;
            } else {
                //后一个位置的经度和纬度的分任何一个不落在前一个位置分的范围，则判定不为一组
                if ((that.lng.getMinutes() < lng.getMinMinutes() || that.lng.getMinutes() > lng.getMaxMinutes()) || (that.lat.getMinutes() < lat.getMinMinutes() || that.lat.getMinutes() > lat.getMaxMinutes())) {
                    return false;
                } else {
                    return true;
                }
            }

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = lng != null ? lng.getDegrees().hashCode() : 0;
        result = 31 * result + (lat != null ? lat.getDegrees().hashCode() : 0);
        return result;
    }

    public static RecommendPositionDMS covertToRecommendPositionDMS(PositionDMS positionDMS, int lngInterval, int latInterval) {
        RecommendPositionDMS recommendPositionDMS = new RecommendPositionDMS();
        if (positionDMS != null) {
            if (positionDMS.getLng() != null && positionDMS.getLat() != null) {
                RecommendDMS recommendLng = RecommendDMS.convertRecommendDMS(positionDMS.getLng(), lngInterval);
                RecommendDMS recommendLat = RecommendDMS.convertRecommendDMS(positionDMS.getLat(), latInterval);
                recommendPositionDMS.setLng(recommendLng);
                recommendPositionDMS.setLat(recommendLat);
            }
        }
        return recommendPositionDMS;
    }


    @Override
    public String toString() {
        return "RecommendPositionDMS{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
