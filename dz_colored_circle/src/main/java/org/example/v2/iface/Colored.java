package org.example.v2.iface;

public interface Colored {
    default void setColor(int color){}
    int getColor();
}
