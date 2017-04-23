package ru.stqa.pft.sandbox.task2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Nikolay Pechenin on 23.04.2017.
 */
public class DistanceTest {

    @Test
    public void testStatic() {
        Point p1 = new Point(-5 , 0);
        Point p2 = new Point(5, 0);
        Assert.assertEquals(Point.distance(p1, p2), 10.0);

        p1.x = 0;
        p1.y = -5;
        p2.x = 0;
        p2.y = 5;
        Assert.assertEquals(Point.distance(p1, p2), 10.0);

        p1.x = 0;
        p1.y = 0;
        p2.x = 0;
        p2.y = 0;
        Assert.assertEquals(Point.distance(p1, p2), 0.0);

        p1.x = -1.1;
        p1.y = -1.1;
        p2.x = 1.1;
        p2.y = 1.1;
        Assert.assertEquals(Point.distance(p1, p2), 3.111269837220809);
    }

    @Test
    public void testMethod() {
        Point p1 = new Point(-5 , 0);
        Point p2 = new Point(5, 0);
        Assert.assertEquals(p1.distance(p2), 10.0);

        p1.x = 0;
        p1.y = -5;
        p2.x = 0;
        p2.y = 5;
        Assert.assertEquals(p1.distance(p2), 10.0);

        p1.x = 0;
        p1.y = 0;
        p2.x = 0;
        p2.y = 0;
        Assert.assertEquals(p1.distance(p2), 0.0);

        p1.x = -1.1;
        p1.y = -1.1;
        p2.x = 1.1;
        p2.y = 1.1;
        Assert.assertEquals(p2.distance(p1), 3.111269837220809);

    }
}
