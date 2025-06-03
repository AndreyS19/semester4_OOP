package org.example.v2;




import org.example.v2.iface.HasArea;
import org.example.v2.iface.Movable;
import org.example.v2.iface.Resizable;
import org.example.v2.iface.Stretchable;

import java.awt.*;
import java.util.Objects;
import processing.core.PApplet;
public class Circle extends Figure implements HasArea, Resizable, Movable {
    private int radius;
    private org.example.v2.Point center;
    private PApplet sketch;
    private float xSpeed;
    private float ySpeed;
    public Circle(org.example.v2.Point center, int radius, PApplet sketch) {
        this.center = center;
        this.radius = radius;
        this.sketch = sketch;
    }

    public Circle(int xCenter, int yCenter, int radius, PApplet sketch) {

        this(new org.example.v2.Point(xCenter,yCenter),radius,sketch);
    }

    public Circle(int radius, PApplet sketch) {
        this(new org.example.v2.Point(0,0),radius,sketch);
    }

    //public Circle() {
    //    this(new org.example.v2.Point(0,0),1,sketch);
    //}

    public org.example.v2.Point getCenter(){ return center;}

    public int getRadius() { return radius; }

 	public void setCenter(org.example.v2.Point center) {
        this.center = center;
    }

 	public void setRadius(int radius) {
 	    this.radius = radius;
    }
    @Override
 	public void moveTo(int x, int y) {
 	    center.moveTo(x, y);
    }
    @Override
    public void moveTo(org.example.v2.Point point) {
        center = point;
    }
    @Override
    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
      /*  if(x < 0 || x > sketch.width){
            xSpeed *= -1;
        }

        y += ySpeed;
        if(y < 0 || y > sketch.height){
            ySpeed *= -1;
        }*/
    }
    @Override
    public void resize(double ratio) {
        radius = (int)(radius *ratio);
    }
    @Override
    public double getArea() {
        return Math.PI* radius * radius;
    }
    @Override
 	public double getPerimeter() {
 	    return 2*Math.PI* radius;
    }


    @Override
    public boolean isInside(int x, int y) {
        int xCenter = center.getX();
        int yCenter = center.getY();
        return ((xCenter-x)*(xCenter-x)+(yCenter-y)*(yCenter-y)) <= radius * radius;
    }
    @Override
    public boolean isInside(org.example.v2.Point point) {
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
