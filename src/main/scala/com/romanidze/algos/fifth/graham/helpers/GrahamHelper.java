package com.romanidze.algos.fifth.graham.helpers;

import com.romanidze.algos.fifth.graham.dto.PointDTO;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class GrahamHelper {

    public static int computeOrientation(PointDTO a, PointDTO b, PointDTO c){

        double value = (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());

        if(value < 0){
            return -1; //правая тройка векторов
        }else if(value > 0){
            return 1; //левая тройка векторов
        }

        return 0; //вектора коллинеарны

    }

    public static void computeGraham(PointDTO[] points){

        Stack<PointDTO> hull = new Stack<>();

        Arrays.sort(points);
        Arrays.sort(points, 1, points.length, new GrahamComparator(points[0]));

        hull.push(points[0]);

        int k1;

        for(k1 = 1; k1 < points.length; k1++){
            if(!points[0].equals(points[k1])){
                break;
            }
        }

        if (k1 == points.length){
            throw new NoSuchElementException("Всё полученные точки равны");
        }

        int k2;

        for(k2 = k1 + 1; k2 < points.length; k2++){

            if(computeOrientation(points[0], points[k1], points[k2]) != 0){
                break;
            }

        }

        hull.push(points[k2 - 1]);

        for (int i = k2; i < points.length; i++){

            PointDTO top = hull.pop();

            while (computeOrientation(hull.peek(), top, points[i]) <= 0){
                top = hull.pop();
            }

            hull.push(top);
            hull.push(points[i]);

        }

        int N = hull.size();

        PointDTO[] newPoints = new PointDTO[N];
        int n = 0;

        for(PointDTO p: hull){
            newPoints[n++] = p;
        }

        for(int i = 0; i < N; i++){

            if(computeOrientation(newPoints[i], newPoints[(i + 1) % N], newPoints[(i + 2) % N]) <= 0){
                throw new IllegalArgumentException("Оболочка сформирована не до конца");
            }

        }

        hull.forEach(System.out::println);

    }

}
