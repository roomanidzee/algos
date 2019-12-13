package com.romanidze.algos.nineth.salesman;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class LengthCalculator {

    public static double calculateLength(List<Point2D> cities){

        double result = 0;
        Point2D prev = cities.get(cities.size() - 1);

        for (Point2D city : cities) {
            result += city.distance(prev);
            prev = city;
        }
        return result;

    }

}
