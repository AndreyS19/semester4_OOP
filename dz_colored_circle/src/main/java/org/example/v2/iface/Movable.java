package org.example.v2.iface;
import org.example.v2.Point;

public interface Movable {
    default void moveTo(int x, int y){};
    default void moveTo(Point point) {};
    default void moveRel(int dx, int dy){};
}
