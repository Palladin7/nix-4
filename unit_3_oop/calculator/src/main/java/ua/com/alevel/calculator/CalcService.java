package ua.com.alevel.calculator;

import java.math.BigInteger;

public interface CalcService {
    BigInteger sum(String num1, String num2);
    BigInteger subtract(String num1, String num2);
    BigInteger multiply(String num1, String num2);
}
