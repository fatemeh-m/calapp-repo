/**
 * This Class represents a simple mathematical expression
 * including two operand and one operator
 */
public class Expression {
    private final double operand1;
    private final double operand2;
    private final char operator;
    private double result;

    public Expression(double operand1, double operand2, char operator){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
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
}
