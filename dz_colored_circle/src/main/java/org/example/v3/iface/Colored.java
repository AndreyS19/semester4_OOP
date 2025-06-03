package org.example.v3.iface;

import org.example.v3.colors.ColorException;

public interface Colored {
    void setColor(String colorString) throws ColorException;

    String getColor();
}
