package ua.com.alevel.test;

import ua.com.alevel.calculator.CalcService;
import ua.com.alevel.calculator.factory.CalcFactory;
import ua.com.alevel.console.ConsoleService;
import ua.com.alevel.console.factory.ConsoleFactory;

import java.math.BigInteger;

public class Test {
    private final CalcService calcService = CalcFactory.getInstance().getCalcService();
    private final ConsoleService consoleService = ConsoleFactory.getInstance().getConsoleService();

    public void run() {
        String num1 = "8738487287243";
        String num2 = "7298634872347";

        BigInteger sum = calcService.sum(num1, num2);
        consoleService.println(sum);
        consoleService.println();
    }
}
