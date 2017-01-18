package com.wy;

import java.util.Arrays;
import java.util.Vector;

/**
 * 求一组位置数据的排列组合
 */
public class PositionsCombination {

    public static final int maxCombinationNumber = 2;//支持两两组合情况

    public static void main(String[] args) {
        Position position0 = new Position(117.539905, 36.719823);
        Position position1 = new Position(118.51626, 25.962553);
        Position position2 = new Position(119.51626, 25.962553);
        Vector testData = new Vector(Arrays.asList(position0, position1, position2));
        Vector results = getAllCombinations(testData);
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.elementAt(i));
        }
    }

    public static Vector getAllCombinations(Vector data) {
        Vector allCombinations = new Vector();
        for (int i = 1; i <= data.size(); i++) {
            Vector initialCombination = new Vector();
            Vector allCombinations1 = getAllCombinations(data, i);
            if (allCombinations1 == null) {
                continue;
            }
            allCombinations.addAll(allCombinations1);
        }

        return allCombinations;
    }

    public static Vector getAllCombinations(Vector data, int length) {
        Vector allCombinations = new Vector();
        Vector initialCombination = new Vector();
        if (length != maxCombinationNumber) {
            return null;
        }
        combination(allCombinations, data, initialCombination, length);
        return allCombinations;
    }

    private static void combination(Vector allCombinations, Vector data,
                                    Vector initialCombination, int length) {
        if (length == 1) {
            for (int i = 0; i < data.size(); i++) {
                Vector newCombination = new Vector(initialCombination);
                newCombination.add(data.elementAt(i));
                allCombinations.add(newCombination);
            }
        }

        if (length > 1) {
            for (int i = 0; i < data.size(); i++) {
                Vector newCombination = new Vector(initialCombination);
                newCombination.add(data.elementAt(i));

                Vector newData = new Vector(data);
                for (int j = 0; j <= i; j++)
                    newData.remove(data.elementAt(j));

                combination(allCombinations, newData, newCombination, length - 1);
            }
        }
    }
}
