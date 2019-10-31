package com.romanidze.algos.fifth.jarvis.helpers;

import com.romanidze.algos.fifth.jarvis.dto.PointDTO;

import java.text.MessageFormat;
import java.util.Vector;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class JarvisHelper {

    private PointDTO[] points;

    public JarvisHelper(PointDTO[] points){
        this.points = points;
    }

    private int computeOrientation(PointDTO p, PointDTO q, PointDTO r){

        //ориентация тройки векторов (правая / левая)
        int value =
                (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                        (q.getX() - p.getX()) * (r.getY() - q.getY());

        //0 - вектора коллинеарны
        //1 - левая тройка векторов
        //2 - правая тройка векторов

        if(value == 0){
            return 0;
        }

        return (value > 0) ? 1 : 2;

    }

    public void computeJarvis(){

        if(this.points.length < 3){
            throw new IllegalArgumentException("Должно быть хотя бы 3 точки");
        }

        Vector<PointDTO> jarvisResult = new Vector<>();

        int leftMostPoint = 0;

        for(int i = 1; i < this.points.length; i++){

            if(this.points[i].getX() < this.points[leftMostPoint].getX()){
                leftMostPoint = i;
            }

        }

        int p = leftMostPoint, q;

        do{

            jarvisResult.add(this.points[p]);

            //ищём такую точку q, чтобы тройка векторов (p, x, q) была правой со всеми точками х
            q = (p + 1) % this.points.length;

            for(int i = 0; i < this.points.length; i++){

                if(this.computeOrientation(this.points[p], this.points[i], this.points[q]) == 2){
                    q = i;
                }

            }

            p = q;

        } while (p != leftMostPoint);

        String formatString = "({0}, {1})";

        jarvisResult.forEach(item -> System.out.println(MessageFormat.format(formatString, item.getX(), item.getY())));

    }

}
