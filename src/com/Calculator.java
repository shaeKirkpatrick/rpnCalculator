package com;

import com.exception.CalculatorException;
import com.util.OperatorUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;

public class Calculator {

    private RealNumberStack stack;
    private Stack<String> history;
    private int position;
    private String errorOperator = "";

    public Calculator(int scale, int precision){
        stack = new RealNumberStack(scale, precision);
        history = new Stack<>();
        position = 0;
        errorOperator = "";
    }

    public String calculate(String input) {
        Arrays.asList(input.split(" ")).stream().forEach(element -> {
            if (!stack.error()) {
                history.push(element);
                performOperation(element);
            }
        });

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
        try {
            position++;
            switch (element) {
                case "+":
                    applyOperation(stack, (rhs, lhs) -> lhs.add(rhs));
                    break;
                case "-":
                    applyOperation(stack, (rhs, lhs) -> lhs.subtract(rhs));
                    break;
                case "*":
                    applyOperation(stack, (rhs, lhs) -> lhs.multiply(rhs));
                    break;
                case "/":
                    applyOperation(stack, (rhs, lhs) -> lhs.divide(rhs, lhs.scale(), BigDecimal.ROUND_HALF_UP));
                    break;
                case "sqrt":
                    double evaluatedValue = Math.sqrt(new Double(stack.pop().toString()));
                    stack.push(new BigDecimal(evaluatedValue).setScale(stack.getScale(), BigDecimal.ROUND_HALF_UP));
                    break;
                case "undo":
                    history.pop();
                    performUndo();
                    break;
                case "clear":
                    clear();
                    break;
                default:
                    stack.push(new BigDecimal(element).setScale(stack.getScale(), BigDecimal.ROUND_HALF_UP));
                    break;
            }
            position++;

        }catch (CalculatorException e){
            errorOperator = element;
        }
    }

    private void performUndo() {
        String lastOperation = history.pop();
        String lastDigit = history.pop();

        if (OperatorUtil.isOperator(lastOperation)){
            if (OperatorUtil.isSqrt(lastOperation)) {
                stack.pop();
                performOperation(lastDigit);
            } else {
                performOperation(lastDigit);
                performOperation(OperatorUtil.reverseOperator(lastOperation));
                performOperation(lastDigit);
            }
        } else {
            stack.pop();
        }
    }

    private void applyOperation(RealNumberStack stack, BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) throws CalculatorException {
        try {
            if (stack.size() > 1) {
                BigDecimal result = operation.apply(stack.pop(), stack.pop()).setScale(stack.getScale());
                stack.push(result);
            } else {
                stack.setError();
                throw new CalculatorException();
            }
        } catch (EmptyStackException e) {
            throw new CalculatorException();
        }
    }
}
