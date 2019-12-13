package com.romanidze.algos.nineth.salesman;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        var filePath = "src/main/resources/salesman_data.tsp";

        List<Point2D> cities = new ArrayList<>(
                Loader.prepareData(filePath)
        );


        double length = LengthCalculator.calculateLength(cities);
        System.out.println("Длина пути: " + length);
        System.out.println(" ");

        List<Point2D> neighbours = NeighbourHelper.nearest(cities);
        double neighbourLength = LengthCalculator.calculateLength(neighbours);

        System.out.println("Алгоритм поиска ближайших соседей");
        System.out.println("Решение: " + neighbours.toString());
        System.out.println("Длина пути: " + neighbourLength);

        if(Set.copyOf(neighbours).size() == neighbours.size()){
            System.out.println("Построенный путь верен");
        }else{
            System.out.println("Построенный путь неверен");
        }

        System.out.println(" ");

        List<Point2D> result = TwoOpt.alternate(neighbours);
        double twoOptLength = LengthCalculator.calculateLength(result);

        System.out.println("Двуоптимальная оптимизация");
        System.out.println("Решение: " + result.toString());
        System.out.println("Длина пути: " + twoOptLength);

        if(Set.copyOf(result).size() == result.size()){
            System.out.println("Построенный путь верен");
        }else{
            System.out.println("Построенный путь неверен");
        }

        System.out.println(" ");

    }

}
