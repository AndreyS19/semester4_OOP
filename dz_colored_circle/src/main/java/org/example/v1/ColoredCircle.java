package org.example.v1;

import java.util.Objects;

public class ColoredCircle extends Circle {
    private int color;

    public ColoredCircle(Point center, int radius, int color) {
        super(center, radius);
        this.color = color;
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
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
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
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
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }
}
