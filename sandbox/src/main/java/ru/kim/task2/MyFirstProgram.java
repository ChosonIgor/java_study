package ru.kim.task2;

public class MyFirstProgram {
    public static void main(String[] args) {
		Point point1 = new Point(5, 6);
		Point point2 = new Point(1, 3);
		System.out.println(distance(point1, point2));
		System.out.println(point2.distance(point1));
		System.out.println(point1.distance(point2));
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.pX - p2.pX), 2) + Math.pow((p1.pY - p2.pY), 2));
	}
}