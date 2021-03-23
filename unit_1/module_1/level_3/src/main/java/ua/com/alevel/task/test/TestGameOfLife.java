package ua.com.alevel.task.test;

import ua.com.alevel.task.GameOfLife;

import java.util.Scanner;

public class TestGameOfLife {

    public static void run() {
        System.out.println("-----------------------Game Of Life---------------------");
        GameOfLife.printBoard();

        for (int i = 0; i < 5; i++) {
            GameOfLife.nextState();
        }

        boolean finish = false;
        String input;
        while (!finish) {
            System.out.print("Calculate next 5 states? (Y / N): ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (!input.equals("Y") && !input.equals("N")) {
                System.out.println("Invalid input\n");
                continue;
            }

            switch (input) {
                case "N":
                    System.out.println("Have a nice day!");
                    finish = true;
                    break;
                case "Y":
                    System.out.println();
                    for (int i = 0; i < 5; i++) {
                        GameOfLife.nextState();
                    }
            }
        }
        System.out.println("--------------------------------------------------------");
    }
}
