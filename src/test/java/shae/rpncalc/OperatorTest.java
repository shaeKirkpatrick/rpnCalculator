package shae.rpncalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void getOperator() {
        assertEquals(Operator.ADD, Operator.getOperator("+"));
        assertEquals(Operator.SUBTRACT, Operator.getOperator("-"));
        assertEquals(Operator.DIVIDE, Operator.getOperator("/"));
        assertEquals(Operator.MULTIPLY, Operator.getOperator("*"));
        assertEquals(Operator.SQRT, Operator.getOperator("sqrt"));
        assertEquals(Operator.CLEAR, Operator.getOperator("clear"));
        assertEquals(Operator.UNDO, Operator.getOperator("undo"));
        assertEquals(Operator.NOT_OPERATOR, Operator.getOperator("10"));
    }

    @Test
    void getSymbol() {
        assertEquals(Operator.ADD.getSymbol(), "+");
        assertEquals(Operator.SUBTRACT.getSymbol(),"-");
        assertEquals(Operator.DIVIDE.getSymbol(), "/");
        assertEquals(Operator.MULTIPLY.getSymbol(), "*");
        assertEquals(Operator.SQRT.getSymbol(), "sqrt");
        assertEquals(Operator.CLEAR.getSymbol(), "clear");
        assertEquals(Operator.UNDO.getSymbol(), "undo");
    }

    @Test
    void getReverseOperator() {
        assertEquals(Operator.ADD.getReverseOperator(), "-");
        assertEquals(Operator.SUBTRACT.getReverseOperator(),"+");
        assertEquals(Operator.DIVIDE.getReverseOperator(), "*");
        assertEquals(Operator.MULTIPLY.getReverseOperator(), "/");
        assertEquals(Operator.SQRT.getReverseOperator(), "");
        assertEquals(Operator.CLEAR.getReverseOperator(), "");
        assertEquals(Operator.UNDO.getReverseOperator(), "");
    }
}