package ua.com.alevel.console.impl;

import ua.com.alevel.console.ConsoleService;

import java.util.Scanner;

public class DefaultConsoleService implements ConsoleService {
    public void print(Object o) {
        System.out.print(o);
    }

    public void println() {
        System.out.println();
    }

    public void println(Object o) {
        System.out.println(o);
    }

    public String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
