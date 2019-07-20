package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator(15, 10);

    @BeforeEach
    void clearStack(){
        calculator.clear();
    }

    @Test
    void calculateExample1() {
        String result = calculator.calculate("5 2");
        assertEquals("stack: 5 2", result);
    }

    @Test
    void calculateExample2() {
        String result = calculator.calculate("2 sqrt");
        assertEquals("stack: 1.4142135624", result);

        result = calculator.calculate("clear 9 sqrt");
        assertEquals("stack: 3", result);
    }

    @Test
    void calculateExample3() {
        String result = calculator.calculate("5 2 -");
        assertEquals("stack: 3", result);

        result = calculator.calculate("3 -");
        assertEquals("stack: 0", result);

        result = calculator.calculate("clear");
        assertEquals("stack:", result);
    }

    @Test
    void calculateExample4() {
        String result = calculator.calculate("5 4 3 2");
        assertEquals("stack: 5 4 3 2", result);

        result = calculator.calculate("undo undo *");
        assertEquals("stack: 20", result);

        result = calculator.calculate("5 *");
        assertEquals("stack: 100", result);

        result = calculator.calculate("undo");
        assertEquals("stack: 20 5", result);
    }

    @Test
    void calculateExample5() {
        String result = calculator.calculate("7 12 2 /");
        assertEquals("stack: 7 6", result);

        result = calculator.calculate("*");
        assertEquals("stack: 42", result);

        result = calculator.calculate("4 /");
        assertEquals("stack: 10.5", result);
    }

    @Test
    void calculateExample6() {
        String result = calculator.calculate("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", result);

        result = calculator.calculate("*");
        assertEquals("stack: 1 2 3 20", result);

        result = calculator.calculate("clear 3 4 -");
        assertEquals("stack: -1", result);
    }

    @Test
    void calculateExample7() {
        String result = calculator.calculate("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", result);

        result = calculator.calculate("* * * *");
        assertEquals("stack: 120", result);
    }

   @Test
    void calculateExample8() {
        String result = calculator.calculate("1 2 3 * 5 + * * 6 5");
        String[] resultSet = result.split("\n");

        assertEquals("operator * (position: 15): insufficient parameters", resultSet[0]);
        assertEquals("stack: 11", resultSet[1]);

    }

   @Test
   void calculateUndoAdd(){
       String result = calculator.calculate("1 2 +");
       assertEquals("stack: 3", result);

       result = calculator.calculate("undo");
       assertEquals("stack: 1 2", result);
   }

    @Test
    void calculateUndoSubtract(){
        String result = calculator.calculate("2 1 -");
        assertEquals("stack: 1", result);

        result = calculator.calculate("undo");
        assertEquals("stack: 2 1", result);
    }

    @Test
    void calculateUndoMultiply(){
        String result = calculator.calculate("2 3 *");
        assertEquals("stack: 6", result);

        result = calculator.calculate("undo");
        assertEquals("stack: 2 3", result);
    }

    @Test
    void calculateUndoDivide(){
        String result = calculator.calculate("4 2 /");
        assertEquals("stack: 2", result);

        result = calculator.calculate("undo");
        assertEquals("stack: 4 2", result);
    }

    @Test
    void calculateUndoSqrt(){
        String result = calculator.calculate("64 sqrt");
        assertEquals("stack: 8", result);

        result = calculator.calculate("undo");
        assertEquals("stack: 64", result);
    }
}