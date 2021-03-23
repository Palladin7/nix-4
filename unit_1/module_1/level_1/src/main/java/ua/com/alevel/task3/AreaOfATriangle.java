package ua.com.alevel.task3;

import ua.com.alevel.task3.point.Point;

public class AreaOfATriangle {

    public static double findArea(Point A, Point B, Point C) {
        double area = (A.getX() * (B.getY() - C.getY()) +
                B.getX() * (C.getY() - A.getY()) +
                C.getX() * (A.getY() - B.getY())) / 2.0;
        return Math.abs(area);
    }
}
