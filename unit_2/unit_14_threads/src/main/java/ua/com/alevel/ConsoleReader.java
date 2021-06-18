package ua.com.alevel;

import java.util.Scanner;

public class ConsoleReader extends Thread {

    private final StringBuilder input = new StringBuilder("");

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String nextLine = scanner.nextLine();

            if (!nextLine.contains("quit")) {
                input.append(nextLine);
                input.append("\n");
            } else {
                input.append(nextLine, 0, nextLine.indexOf("quit"));
                break;
            }
        }
    }

    public String getInput() {
        return input.toString();
    }
}
