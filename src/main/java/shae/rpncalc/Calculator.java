package shae.rpncalc;

import shae.rpncalc.exception.InsufficientParametersException;
import java.util.Arrays;
import java.util.EmptyStackException;

import java.math.BigDecimal;
import java.util.Stack;

import static shae.rpncalc.Operator.NOT_OPERATOR;

public class Calculator {

    /* Stack to store numbers */
    private NumberStack stack;

    /* Stack to store history of user input */
    private Stack<String> history;

    /* Position in user input string */
    private int position;

    /* Operator that has caused an error */
    private String errorOperator;

    public Calculator(int scale, int precision){
        stack = new NumberStack(scale, precision);
        history = new Stack<>();
        position = 0;
        errorOperator = "";
    }

    public String calculate(String input) {
        /* For each user input element, perform appropriate operation and save to history */
        Arrays.asList(input.split(" ")).stream().forEach(element -> {
            if (!stack.error()) {
                history.push(element);
                performOperation(element);
            }
        });

        position = 0;

        if (stack.error()){
            return String.format("operator %s (position: %d): insufficient parameters\n%s",
                    errorOperator, position, stack.getResult());
        } else {
            return stack.getResult();
        }
    }

    public void clear() {
        history.clear();
        stack.clear();
        position = 0;
    }

    private void performOperation(String element) {
        Operator operator = Operator.getOperator(element);
        try {
            position++;
            switch (operator) {
                case ADD:
                case SUBTRACT:
                case MULTIPLY:
                case DIVIDE:
                case SQRT:
                    applyOperation(operator);
                    break;
                case UNDO:
                    history.pop();
                    performUndo();
                    break;
                case CLEAR:
                    clear();
                    break;
                default:
                    stack.push(new BigDecimal(element).setScale(stack.getScale(), BigDecimal.ROUND_HALF_UP));
                    break;
            }
            position++;

        }catch (InsufficientParametersException e){
            errorOperator = element;
        }
    }

    private void performUndo() throws InsufficientParametersException {
        if (history.size() > 0) {
            Operator operator = Operator.getOperator(history.pop());

            /* If operator, undo operation. If number, undo adding to stack */
            if (operator != NOT_OPERATOR) {
                String lastDigit = history.pop();
                if (operator.equals(Operator.SQRT)) {
                    stack.pop();
                    performOperation(lastDigit);
                } else {
                    performOperation(lastDigit);
                    performOperation(operator.getReverseOperator());
                    performOperation(lastDigit);
                    history.push(lastDigit);
                }
            } else {
                stack.pop();
            }
        } else {
            throw new InsufficientParametersException();
        }
    }

    private void applyOperation(Operator operator) throws InsufficientParametersException {
        try {
            boolean isSqrt = operator.equals(Operator.SQRT);

            /* Ensure there are enough parameters on stack for operation, then apply */
            if ((stack.size() > 1) || (stack.size() == 1 && isSqrt)){
                BigDecimal result = isSqrt
                        ? operator.apply(stack.pop(), null)
                        : operator.apply(stack.pop(), stack.pop()).setScale(stack.getScale());

                stack.push(result);
            } else {
                stack.setError();
                throw new InsufficientParametersException();
            }
        } catch (EmptyStackException e) {
            throw new InsufficientParametersException();
        }
    }
}
