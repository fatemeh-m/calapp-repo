package com.mycompany.app;

public class Addition extends Operation {
    @Override
    public double calculateResult(double op1, double op2) {
        return op1 + op2;
    }
}