package org.example.v2;
import java.util.Objects;
public abstract class Figure {

    public abstract double getPerimeter();
    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
    public abstract boolean equals(Object o);
    public abstract int hashCode();
}
