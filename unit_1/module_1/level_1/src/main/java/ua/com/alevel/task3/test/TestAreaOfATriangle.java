package ua.com.alevel.task3.test;

import ua.com.alevel.task3.AreaOfATriangle;
import ua.com.alevel.task3.point.Point;

public class TestAreaOfATriangle {

    public static void run() {
        Point A1 = new Point(5,2);
        Point B1 = new Point(15,15);
        Point C1 = new Point(10, 5);

        Point A2 = new Point(12,7);
        Point B2 = new Point(21,1);
        Point C2 = new Point(16, 18);

        System.out.println("---------------------Area of a triangle--------------------");
        System.out.println("Given 3 points: A(" + A1.getX() + ", " + A1.getY() + "), " +
                "B(" + B1.getX() + ", " + B1.getY() + "), " +
                "C(" + C1.getX() + ", " + C1.getY() + ")");
        System.out.println("Area of a triangle: " + AreaOfATriangle.findArea(A1, B1, C1));

        System.out.println();

        System.out.println("Given 3 points: A(" + A2.getX() + ", " + A2.getY() + "), " +
                "B(" + B2.getX() + ", " + B2.getY() + "), " +
                "C(" + C2.getX() + ", " + C2.getY() + ")");
        System.out.println("Area of a triangle: " + AreaOfATriangle.findArea(A2, B2, C2));
        System.out.println("-----------------------------------------------------------");
    }
}
