package org.example;//https://happycoding.io/tutorials/java/processing-in-java
import java.util.ArrayList;
import java.util.List;

import org.example.v3.Circle;
import org.example.v3.ColoredCircle;
import org.example.v3.Figure;
import org.example.v3.Point;
import org.example.v3.colors.Color;
import org.example.v3.colors.ColorErrorCode;
import org.example.v3.colors.ColorException;
import processing.core.PApplet;

import static org.example.v3.colors.Color.RED;
import static org.example.v3.colors.Color.colorFromString;

public class MyApp extends PApplet{

    //private ArrayList<ColoredCircle> circles = new ArrayList<>();
    private List<Figure> figures = new ArrayList<>();

    public void settings(){
        size(800, 800);
        try {
            //circles.add(new ColoredCircle(this,new Point(width/2,height/2),"GREEN"));
            figures.add(new ColoredCircle(this,new Point(width/2,height/2),"GREEN"));
        } catch (ColorException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void draw(){
        background(20,20,20);
        for(Figure f : figures){
            f.moveRel();
            f.render(this);
        }
    }

    public void mouseDragged(){
        try {
            //circles.add(new ColoredCircle(this,new Point(mouseX, mouseY), "GREEN"));
            figures.add(new ColoredCircle(this,new Point(mouseX, mouseY),"GREEN"));
        } catch (ColorException e) {
            System.out.println(e.getErrorCode());
        }
    }
    public void mousePressed(){
        if (mouseButton==RIGHT)
        {
            figures.clear();
        }

    }
    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        MyApp mySketch = new MyApp();
        PApplet.runSketch(processingArgs, mySketch);
    }
}