package org.example.v3;

import org.example.v3.iface.Movable;

public class Point implements Movable {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void moveTo(int newX, int newY) {
        x = newX;
        y = newY;
    }

    @Override
    public void moveTo(Point point) {

    }

    /*@Override
    public void moveRel() {


    }*/
    public void moveRel() {
        x += 10;
        y += 10;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        return y == other.y;
    }

//    @Override
//    public String toString() {
//        return "Point{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }
}
