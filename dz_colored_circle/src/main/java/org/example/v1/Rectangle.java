package org.example.v1;

import java.util.Objects;

public class Rectangle {
    private Point pointTopLeft;
    private Point pointBottomRight;

    public Rectangle(Point leftTop, Point rightBottom) {
        pointTopLeft = leftTop;
        pointBottomRight = rightBottom;
        //this(leftTop.getX(),leftTop.getY(),rightBottom.getX(),rightBottom.getY());
    }

 	public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this(new Point(xLeft, yTop),new Point(xRight, yBottom));
    }

 	public Rectangle(int length, int width) {this(new Point(0,-width),new Point(length,0));}

 	public Rectangle() {this(new Point(0,-1),new Point(1,0));}

 	public void setTopLeft(Point topLeft) {pointTopLeft = topLeft;}

 	public final void setBottomRight(Point bottomRight) {pointBottomRight = bottomRight;}

    public Point getTopLeft() {return pointTopLeft;}

    public Point getBottomRight() {return pointBottomRight;}

    public int getLength() { return  pointBottomRight.getX() - pointTopLeft.getX(); }

    public final int getWidth() { return  pointBottomRight.getY() - pointTopLeft.getY(); }

    public void moveTo(int x, int y) {
        pointBottomRight.moveTo(x+getLength(), y+getWidth());
        pointTopLeft.moveTo(x, y);

    }

    public void moveTo(Point point) {
        pointBottomRight.moveTo(point.getX()+getLength(), point.getY()+getWidth());
        pointTopLeft = point;
        //this.moveTo(point.getX(),point.getY());
    }

    public void moveRel(int dx, int dy) {
        pointTopLeft.moveRel(dx, dy);
        pointBottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        pointBottomRight.moveRel((int)(getLength()*ratio)-getLength(),(int)(getWidth()*ratio)-getWidth());
    }

    public double getArea() {return getLength()*getWidth();}

    public double getPerimeter() {return 2*(getLength()+getWidth());}

    public boolean isInside(int x, int y) {
        return pointTopLeft.getX() <= x && pointBottomRight.getX() >= x && pointTopLeft.getY() <= y && pointBottomRight.getY() >= y;
    }

    public boolean isInside(Point point) {return isInside(point.getX(),point.getY());}

    public boolean isInside(Rectangle rectangle) {
        return this.isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight());
    }

    public boolean isIntersects(Rectangle rectangle) {
        return getTopLeft().getX() <= rectangle.getBottomRight().getX() &&
                getBottomRight().getX() >= rectangle.getTopLeft().getX() &&
                getTopLeft().getY() <= rectangle.getBottomRight().getY() &&
                getBottomRight().getY() >= rectangle.getTopLeft().getY();
    }

    public final void stretch(double xRatio, double yRatio) {
        pointBottomRight.moveRel((int)(getLength()*xRatio)-getLength(),(int)(getWidth()*yRatio)-getLength());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return pointTopLeft.equals(rectangle.pointTopLeft) &&
                pointBottomRight.equals(rectangle.pointBottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointTopLeft, pointBottomRight);
    }
}
