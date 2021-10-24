package com.mycompany.app;

import java.io.Serializable;

/**
 * This Class represents a simple mathematical expression
 * including two operand and one operator
 */
public class Expression implements Serializable {
    private final double operand1;
    private final double operand2;
    private final char operator;
    private double result;
    private boolean isCalculated;


    public Expression(double operand1, double operand2, char operator){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        isCalculated = false;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public char getOperator() {
        return operator;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result){
        this.result = result;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated() {
        isCalculated = true;
    }

    public String toString(){
        return operand1 + " " + operator + " " + operand2 + " = " + result;
    }

}
