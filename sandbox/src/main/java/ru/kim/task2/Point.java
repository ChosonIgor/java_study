package ru.kim.task2;

public class Point {
    double pX;
    double pY;

    public Point(double pX, double pY) {
        this.pX = pX;
        this.pY = pY;
    }

    public double distance(Point outsidePoint) {
        return Math.sqrt(Math.pow((outsidePoint.pX - pX), 2) + Math.pow((outsidePoint.pY - pY), 2));
    }


}
