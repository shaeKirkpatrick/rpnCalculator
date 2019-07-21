package shae.rpncalc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum Operator {
    ADD("+", "-"){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            return lhs.add(rhs);
        }
    },
    SUBTRACT("-", "+"){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            return lhs.subtract(rhs);
        }
    },
    MULTIPLY("*", "/"){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            return lhs.multiply(rhs);
        }
    },
    DIVIDE("/", "*"){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            return lhs.divide(rhs, lhs.scale(), BigDecimal.ROUND_HALF_UP);
        }
    },
    SQRT("sqrt", ""){
        public BigDecimal apply(BigDecimal lhs, BigDecimal unused){
            double evaluatedValue = Math.sqrt(new Double(lhs.toString()));
            return new BigDecimal(evaluatedValue).setScale(lhs.scale(), BigDecimal.ROUND_HALF_UP);
        }
    },
    UNDO("undo", ""){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            System.out.println("Invalid operation, nothing to apply");
            return null;
        }
    },
    CLEAR("clear", ""){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            System.out.println("Invalid operation, nothing to apply");
            return null;

        }
    },
    NOT_OPERATOR("", ""){
        public BigDecimal apply(BigDecimal rhs, BigDecimal lhs){
            System.out.println("Invalid operation, nothing to apply");
            return null;

        }
    };

    // Store String values in map to save on look up cost
    private static final Map<String, Operator> operatorLookup = new HashMap<String, Operator>();
    static {
        for (Operator o : values()) {
            operatorLookup.put(o.getSymbol(), o);
        }
    }

    private String symbol;
    private String reverseOperator;

    Operator(String symbol, String reverseOperator){
        this.symbol = symbol;
        this.reverseOperator = reverseOperator;
    }

    public abstract BigDecimal apply(BigDecimal rhs, BigDecimal lhs);

    public static Operator getOperator(String operator){
        return operatorLookup.get(operator) == null
                    ? Operator.NOT_OPERATOR
                    : operatorLookup.get(operator);
    }

    public String getSymbol(){
        return this.symbol;
    }

    public String getReverseOperator(){
        return this.reverseOperator;
    }

}

