package ua.com.alevel.task2.test;

import ua.com.alevel.task2.KnightMoveOnChessboard;

public class TestKnightMoveOnChessboard {

    public static void run() {
        String current1 = "3D";
        String desirable1 = "4F";

        String current2 = "8H";
        String desirable2 = "4G";

        System.out.println("------------------------Knight Move------------------------");
        System.out.print("Can you move knight from (" + current1 + ") to [" + desirable1 + "]? ");
        System.out.println(KnightMoveOnChessboard.move(current1, desirable1));
        System.out.println();
        KnightMoveOnChessboard.printChessboard(current1, desirable1);

        System.out.println();
        System.out.print("Can you move knight from (" + current2 + ") to [" + desirable2 + "]? ");
        System.out.println(KnightMoveOnChessboard.move(current2, desirable2));
        System.out.println();
        KnightMoveOnChessboard.printChessboard(current2, desirable2);
        System.out.println("-----------------------------------------------------------");
    }
}
