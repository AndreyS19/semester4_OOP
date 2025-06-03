package org.example.v1;

import java.util.Objects;

public class Circle {
    private int radius;
    private Point center;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this(new Point(xCenter,yCenter),radius);
    }

    public Circle(int radius) {this(new Point(0,0),radius); }

    public Circle() { this(new Point(0,0),1); }

    public Point getCenter(){ return center;}

    public int getRadius() { return radius; }

 	public void setCenter(Point center) {
        this.center = center;
    }

 	public void setRadius(int radius) {
 	    this.radius = radius;
    }

 	public void moveTo(int x, int y) {
 	    center.moveTo(x, y);
    }

    public void moveTo(Point point) {
        center = point;
    }

    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        radius = (int)(radius *ratio);
    }

    public double getArea() {
        return Math.PI* radius * radius;
    }

 	public double getPerimeter() {
 	    return 2*Math.PI* radius;
    }

    public boolean isInside(int x, int y) {
        int xCenter = center.getX();
        int yCenter = center.getY();
        return ((xCenter-x)*(xCenter-x)+(yCenter-y)*(yCenter-y)) <= radius * radius;
    }

    public boolean isInside(Point point) {
        int xCenter = center.getX();
        int yCenter = center.getY();
        return ((xCenter-point.getX())*(xCenter-point.getX())+(yCenter-point.getY())*(yCenter-point.getY())) <= radius * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return getRadius() == circle.getRadius() &&
                getCenter().equals(circle.getCenter());
    }

    @Override
    public int hashCode() { return Objects.hash(getRadius(), getCenter()); }

    public void test(Point p) {
        p.setX(10);
        p.setY(20);
    }
}
