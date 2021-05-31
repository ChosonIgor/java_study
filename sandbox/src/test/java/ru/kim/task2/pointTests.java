package ru.kim.task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class pointTests {

    @Test
    public void testDistance1() {
        Point point1 = new Point(10,20);
        Point point2 = new Point(10,20);
        Assert.assertEquals(point1.distance(point2), 0.0);
    }

    @Test
    public void testDistance2() {
        Point point1 = new Point(10,20);
        Point point2 = new Point(14,17);
        Assert.assertEquals(point1.distance(point2), 5.0);
    }

    @Test
    public void testDistance3() {
        Point point1 = new Point(0,0);
        Point point2 = new Point(14,17);
        Assert.assertEquals(point1.distance(point2), MyFirstProgram.distance(point1, point2));
    }

}
