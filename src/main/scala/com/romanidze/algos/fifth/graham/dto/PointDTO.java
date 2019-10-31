package com.romanidze.algos.fifth.graham.dto;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class PointDTO implements Comparable<PointDTO>{

    private Double x;
    private Double y;

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public int compareTo(PointDTO other) {
        if(this.getY() < other.getY()){
            return -1;
        }

        if(this.getY() > other.getY()){
            return 1;
        }

        if(this.getX() < other.getX()){
            return -1;
        }

        if(this.getX() > other.getX()){
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Point(x = ").append(this.x)
          .append(" , y = ").append(this.y).append(")");

        return sb.toString();

    }
}
