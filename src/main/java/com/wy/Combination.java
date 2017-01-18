package com.wy;

import java.util.Arrays;
import java.util.Vector;

/**
 * 求一组数据的排列组合
 */
public class Combination {
    public static void main(String[] args) {
//        Vector testData = new Vector(Arrays.asList(1, 2, 3, 4, 5, 6));
        Vector testData = new Vector(Arrays.asList(6, 4, 3, 2, 1, 5));
        Vector results = getAllCombinations(testData);
        for (int i = 0; i < results.size(); i++)
            System.out.println(results.elementAt(i));
    }

    public static Vector getAllCombinations(Vector data) {
        Vector allCombinations = new Vector();
        for (int i = 1; i <= data.size(); i++) {
            Vector initialCombination = new Vector();
            allCombinations.addAll(getAllCombinations(data, i));
        }

        return allCombinations;
    }

    public static Vector getAllCombinations(Vector data, int length) {
        Vector allCombinations = new Vector();
        Vector initialCombination = new Vector();
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
