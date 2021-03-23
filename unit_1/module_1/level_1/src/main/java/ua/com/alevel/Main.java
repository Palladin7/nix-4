package ua.com.alevel;

import ua.com.alevel.task1.test.TestNumberOfUniqueSymbols;
import ua.com.alevel.task2.test.TestKnightMoveOnChessboard;
import ua.com.alevel.task3.test.TestAreaOfATriangle;

public class Main {

    public static void main(String[] args) {
        // Unique symbols
        System.out.println();
        TestNumberOfUniqueSymbols.run();

        // Knight move
        System.out.println();
        TestKnightMoveOnChessboard.run();

        // Area of a triangle
        System.out.println();
        TestAreaOfATriangle.run();
    }
}
