package com.mycompany.operators;

import com.mycompany.app.Operation;

public class Division extends Operation {
    private final char KEY = '/';

    @Override
    public char getKey() {
        return KEY;
    }

    @Override
    public double calculateResult(double op1, double op2) {
        return op1 / op2;
    }
}