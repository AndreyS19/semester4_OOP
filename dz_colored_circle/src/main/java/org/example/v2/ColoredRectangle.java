package org.example.v2;

import org.example.v2.Rectangle;
import org.example.v2.iface.Colored;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {
    private int color;

    public ColoredRectangle(org.example.v2.Point leftTop, org.example.v2.Point rightBottom, int color) {
        super(leftTop,rightBottom);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        this(new org.example.v2.Point(xLeft,yTop), new org.example.v2.Point(xRight,yBottom), color);
    }

    public ColoredRectangle(int length, int width, int color) {
        this(new org.example.v2.Point(0,-width),new org.example.v2.Point(length,0), color);
    }

	public ColoredRectangle(int color) {
        this(new org.example.v2.Point(0,-1),new org.example.v2.Point(1,0),color);
    }

    public ColoredRectangle() {
        this(new org.example.v2.Point(0,-1),new Point(1,0),1);
    }
    @Override
 	public int getColor() {
 	    return color;
    }
    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColoredRectangle)) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return getColor() == that.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }
}
