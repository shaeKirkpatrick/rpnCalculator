package shae.rpncalc;

import java.math.BigDecimal;
import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberStackTest {

    NumberStack stack = new NumberStack(15, 10);
    @BeforeEach
     void clearStack(){
        stack.clear();
    }

    @Test
    void popOnEmptyStack() {
        try {
            stack.pop();
        } catch (EmptyStackException e){
            assertTrue(stack.error());
        }
    }

    @Test
    void testGetResult() {
        stack.push(BigDecimal.ONE);
        stack.push(BigDecimal.TEN);
        stack.push(BigDecimal.ZERO);

        assertEquals("stack: 1 10 0", stack.getResult());
    }

    @Test
    void testGetResultTenDigitFormat() {
        stack.push(new BigDecimal("1.234567891234567"));

        assertEquals("stack: 1.2345678912", stack.getResult());
    }
}