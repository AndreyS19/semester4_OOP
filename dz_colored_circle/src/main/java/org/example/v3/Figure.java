package org.example.v3;
import org.example.v3.Point;
import org.example.v3.iface.Movable;
import processing.core.PApplet;

public abstract class Figure implements Movable {
    protected PApplet sketch;
    protected Point pointTopLeft;
    protected float xSpeed;
    protected float ySpeed;

    public Figure(PApplet sketch,Point pointTopLeft)
    {
        this.xSpeed = sketch.random(-10, 10);
        this.ySpeed = sketch.random(-10, 10);
        this.sketch = sketch;
        this.pointTopLeft=pointTopLeft;
    }

    public void render(PApplet sketch) {
    }
    public void moveRel(){

    }


    public Point getTopLeft() {
        return pointTopLeft;
    }

    public void setTopLeft(Point pointTopLeft) {
        this.pointTopLeft = pointTopLeft;
    }
    public void delObj()
    {}

    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
}
