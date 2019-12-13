package com.romanidze.algos.nineth.salesman;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class TwoOpt {

    public static List<Point2D> alternate(List<Point2D> cities) {

        List<Point2D> newTour;
        double bestDist = LengthCalculator.calculateLength(cities);
        double newDist;

        int swaps = 1;
        int improve = 0;
        int iterations = 0;
        long comparisons = 0;

        while (swaps != 0) {
            swaps = 0;


            int i = 1;
            while (i < cities.size() - 2) {

                int j = i + 1;
                while (j < cities.size() - 1) {
                    comparisons++;

                    double first = cities.get(i)
                            .distance(cities.get(i - 1)) + cities.get(j + 1).distance(cities.get(j));

                    double second = cities.get(i)
                            .distance(cities.get(j + 1)) + cities.get(i - 1).distance(cities.get(j));

                    if (first >= second) {

                        newTour = swap(cities, i, j);

                        newDist = LengthCalculator.calculateLength(newTour);

                        if (newDist < bestDist) {
                            cities = newTour;
                            bestDist = newDist;
                            swaps++;
                            improve++;
                        }
                    }
                    j++;
                }
                i++;
            }
            iterations++;
        }
        System.out.println("Количество сравнений: " + comparisons);
        System.out.println("Количество улучшений: " + improve);
        System.out.println("Количество итераций: " + iterations);
        return cities;
    }

    private static List<Point2D> swap(List<Point2D> cities, int i, int j) {

        int size = cities.size();
        List<Point2D> newTour = IntStream.rangeClosed(0, i - 1)
                                         .mapToObj(cities::get)
                                         .collect(Collectors.toList());

        int dec = 0;
        for (int c = i; c <= j; c++) {
            newTour.add(cities.get(j - dec));
            dec++;
        }

        IntStream.range(j + 1, size)
                 .mapToObj(cities::get)
                 .forEachOrdered(newTour::add);

        return newTour;
    }

}
