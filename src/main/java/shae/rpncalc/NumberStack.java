package shae.rpncalc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

public class NumberStack {

    Stack<BigDecimal> stack;
    DecimalFormat df;

    private int precision;
    private int scale;
    private boolean error;

    public NumberStack(int scale, int precision) {
         stack = new Stack<>();

        this.scale = scale;
        this.precision = precision;
        this.error = false;

         df = new DecimalFormat();
         df.setMaximumFractionDigits(this.precision);
         df.setMinimumFractionDigits(0);
         df.setGroupingUsed(false);
    }

    public void push(BigDecimal number){
        stack.push(number);
    }

    public BigDecimal pop() throws EmptyStackException {
        try{
            return stack.pop();
        } catch (EmptyStackException e){
            error = true;
            throw new EmptyStackException();
        }
    }

    public void clear(){
        stack.clear();
        error = false;
    }

    public boolean error(){
        return error;
    }

    public void setError(){
        error = true;
    }

    public int size() {
        return stack.size();
    }

    public final int getScale(){
        return this.scale;
    }

    public String getResult(){
        String stackString = "stack:";

        for(BigDecimal number : stack) {
            String temp = df.format(number);
            stackString += (" " + temp);
        }
        return stackString;
    }
}
