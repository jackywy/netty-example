package com.wy.test;

import com.wy.test.Position;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 计算经纬度之间的距离
 */

public class DistanceCounterTask implements Callable<Map<Position[], Double>> {
    private static final double EARTH_RADIUS = 6378.137;//地球半径
    private List<Position[]> positionsList;
    private final Map<Position[], Double> map;

    public DistanceCounterTask(List<Position[]> positionsList) {
        this.positionsList = positionsList;
        map = new ConcurrentHashMap<Position[], Double>(positionsList.size());
    }

    @Override
    public Map<Position[], Double> call() throws Exception {
        for (int i = 0; i < positionsList.size(); i++) {
            Position[] positions = positionsList.get(i);
            double distance = getDistance(positions[0], positions[1]);
            map.put(positions, distance);
        }
        return map;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public double getDistance(Position position1, Position position2) {
        double radLat1 = rad(position1.getLat());
        double radLat2 = rad(position2.getLat());
        double a = radLat1 - radLat2;
        double b = rad(position1.getLng()) - rad(position2.getLng());
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

}
