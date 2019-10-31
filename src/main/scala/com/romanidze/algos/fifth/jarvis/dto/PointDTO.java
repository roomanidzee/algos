package com.romanidze.algos.fifth.jarvis.dto;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class PointDTO {

    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Point(x = ").append(this.x)
          .append(" , y = ").append(this.y).append(")");

        return sb.toString();

    }

}
