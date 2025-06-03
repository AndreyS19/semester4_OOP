package org.example.v3.iface;
import org.example.v3.Point;

public interface Movable {

     void moveTo(int x, int y);
     void moveTo(Point point);
    void moveRel();
}
