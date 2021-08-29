import java.util.HashMap;
import java.util.InputMismatchException;

/**
 *This class implements a calculator which
 * supports four basic math operations
 */

public class Calculator {
    private HashMap<Character, Operation> operationMap;

    public Calculator() {
        operationMap = new HashMap<>();

        operationMap.put('+', new Addition());
        operationMap.put('-', new Subtraction());
        operationMap.put('*', new Multiplication());
        operationMap.put('/', new Division());
    }

    public void calculate(Expression expression) throws InputMismatchException {
        if (!operationMap.containsKey(expression.getOperator()))
            throw new InputMismatchException();

        Operation operation = operationMap.get(expression.getOperator());
        double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
        expression.setResult(result);
    }
}
