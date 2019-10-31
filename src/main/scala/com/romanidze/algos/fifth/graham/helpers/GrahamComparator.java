package com.romanidze.algos.fifth.graham.helpers;

import com.romanidze.algos.fifth.graham.dto.PointDTO;

import java.util.Comparator;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class GrahamComparator implements Comparator<PointDTO> {

    private PointDTO comparablePoint;

    public GrahamComparator(PointDTO comparablePoint){
        this.comparablePoint = comparablePoint;
    }

    @Override
    public int compare(PointDTO p1, PointDTO p2) {

        double dx1 = p1.getX() - this.comparablePoint.getX();
        double dy1 = p1.getY() - this.comparablePoint.getY();
        double dx2 = p2.getX() - this.comparablePoint.getX();
        double dy2 = p2.getY() - this.comparablePoint.getY();

        if(dy1 >= 0 && dy2 < 0){
            return -1; //p1 выше, p2 ниже
        }else if(dy2 >= 0 && dy1 < 0){
            return 1; // p2 выше, p1 ниже
        }else if(dy1 == 0 && dy2 == 0){// коллинеарны и горизонтальны
            if (dx1 >= 0 && dx2 < 0) {
                return -1;
            }
            else if (dx2 >= 0 && dx1 < 0) {
                return 1;
            }
            else {
                return 0;
            }
        }

        return -GrahamHelper.computeOrientation(this.comparablePoint, p1, p2);

    }
}
