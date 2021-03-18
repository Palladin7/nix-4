package ua.com.alevel.calculator.impl;

import ua.com.alevel.calculator.CalcService;

import java.math.BigInteger;

public class DefaultCalcService implements CalcService {

    public BigInteger sum(String num1, String num2) {
        return new BigInteger(num1).add(new BigInteger(num2));
    }

    public BigInteger subtract(String num1, String num2) {
        return new BigInteger(num1).subtract(new BigInteger(num2));
    }

    public BigInteger multiply(String num1, String num2) {
        return new BigInteger(num1).multiply(new BigInteger(num2));
    }
}
