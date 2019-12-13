package com.romanidze.algos.nineth.salesman;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class NeighbourHelper {

    public static List<Point2D> nearest(List<Point2D> cities){

        List<Point2D> result = new ArrayList<>();

        Point2D currentCity = cities.remove(0);
        Point2D closestCity = cities.get(0);
        Point2D possible;
        double dist;

        result.add(currentCity);

        while (cities.size() > 0) {

            dist = Double.MAX_VALUE;

            for (Point2D city : cities) {
                possible = city;
                if (currentCity.distance(possible) < dist) {
                    dist = currentCity.distance(possible);
                    closestCity = possible;
                }
            }

            currentCity = closestCity;
            cities.remove(closestCity);
            result.add(currentCity);
        }
        return result;
    }

}
