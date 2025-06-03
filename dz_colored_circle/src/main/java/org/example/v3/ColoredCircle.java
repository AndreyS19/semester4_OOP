package org.example.v3;

import org.example.v3.colors.Color;
import org.example.v3.colors.ColorException;
import org.example.v3.iface.Colored;
import processing.core.PApplet;

import java.util.Objects;

import static org.example.v3.colors.Color.colorFromString;

public class ColoredCircle extends Circle implements Colored {
    private String color;

    /*public ColoredCircle(PApplet sketch,Point center, int radius, int color) {
        super(sketch,center, radius);
        this.color = color;
    }*/
    public ColoredCircle(PApplet sketch,Point center, String color) throws ColorException {
        super(sketch,center);
        colorFromString(color);
        this.color = color;
    }

   /* public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
        this(new Point(xCenter,yCenter), radius, color);
    }

    public ColoredCircle(int radius, int color) {
        this(new Point(0,0), radius, color);
    }

    public ColoredCircle(int color) {
        this(new Point(0,0), 1, color);
    }

	public ColoredCircle() {
        this(new Point(0,0), 1, 1);
    }*/
    @Override
    public String getColor(){
        return color;
    }
    @Override
    public void setColor(String color) throws ColorException{
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColoredCircle)) return false;
        if (!super.equals(o)) return false;
        ColoredCircle that = (ColoredCircle) o;
        return getColor() == that.getColor();
    }
    @Override
    public void render(PApplet sketch) {
        //super.render();
        switch (getColor()) {
            case "RED":
                sketch.fill(255, 0, 0);
                break;
            case "GREEN":
                sketch.fill(0, 255, 0);
                break;
            case "BLUE":
                sketch.fill(0, 0, 255);
                break;

        }
        //sketch.fill();
        sketch.ellipse(pointTopLeft.getX(), pointTopLeft.getY(), radius,radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }
}
