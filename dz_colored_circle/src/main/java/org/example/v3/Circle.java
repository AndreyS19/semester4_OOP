package org.example.v3;
import org.example.v3.colors.Color;
import org.example.v3.iface.HasArea;
import org.example.v3.iface.Movable;
import org.example.v3.iface.Resizable;
import processing.core.PApplet;

import java.awt.*;
import java.util.Objects;

public class Circle extends Figure implements HasArea, Resizable {
    protected int radius;
    //private color
    //private PApplet sketch;


    public Circle(PApplet sketch,Point topLeft){
        super(sketch,topLeft);
        //this.sketch = sketch;
        this.radius = (int) sketch.random((float) 10.0, (float) 40.0);

    }

    public Circle(PApplet sketch,org.example.v3.Point topLeft, int radius) {
        super(sketch,topLeft);
        this.pointTopLeft = topLeft;
        this.radius = radius;
        //this.sketch = sketch;
    }

    @Override
    public void render(PApplet sketch) {
        //super.render();
        sketch.ellipse(pointTopLeft.getX(), pointTopLeft.getY(), radius,radius);
    }
/*//public Circle(int xCenter, int yCenter, int radius) {
    //    this(new org.example.v3.Point(xCenter,yCenter),radius);
    //}*/

    //public Circle(int radius, PApplet sketch) {this(new org.example.v3.Point(0,0),radius); }

   // public Circle() {
   //     this(new org.example.v3.Point(0,0),1);
   // }

    public int getRadius() { return radius; }

 	public void setRadius(int radius) {
 	    this.radius = radius;
    }
    @Override
 	public void moveTo(int x, int y) {
 	    pointTopLeft.moveTo(x, y);
    }
    @Override
    public void moveTo(org.example.v3.Point point) {
        pointTopLeft = point;
    }

    @Override
    public void moveRel() {
        int x= pointTopLeft.getX();
        int y= pointTopLeft.getY();
        x += xSpeed;
        if(x < 0 || x > sketch.width){
            xSpeed *= -1;
        }

        y += ySpeed;
        if(y < 0 || y > sketch.height){
            ySpeed *= -1;
        }
        pointTopLeft.setX(x);
        pointTopLeft.setY(y);
    }


    //public void render(){
   //     sketch.ellipse(x, y, size, size);
   // }
    /*@Override
    public void moveRel() {
        x += xSpeed;
        if(x < 0 || x > sketch.width){
            xSpeed *= -1;
        }

        y += ySpeed;
        if(y < 0 || y > sketch.height){
            ySpeed *= -1;
        }
    }*/

    @Override
    public void resize(double ratio) {
        radius = (int)(radius *ratio);
    }
    @Override
    public double getArea() {
        return Math.PI* radius * radius;
    }
 	public double getPerimeter() {
 	    return 2*Math.PI* radius;
    }


    @Override
    public boolean isInside(int x, int y) {
        int xCenter = pointTopLeft.getX();
        int yCenter = pointTopLeft.getY();
        return ((xCenter-x)*(xCenter-x)+(yCenter-y)*(yCenter-y)) <= radius * radius;
    }
    @Override
    public boolean isInside(org.example.v3.Point point) {
        int xCenter = pointTopLeft.getX();
        int yCenter = pointTopLeft.getY();
        return ((xCenter-point.getX())*(xCenter-point.getX())+(yCenter-point.getY())*(yCenter-point.getY())) <= radius * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return getRadius() == circle.getRadius() &&
                getTopLeft().equals(circle.getTopLeft());
    }

    @Override
    public int hashCode() { return Objects.hash(getRadius(), getTopLeft()); }

    public void test(Point p) {
        p.setX(10);
        p.setY(20);
    }
}
