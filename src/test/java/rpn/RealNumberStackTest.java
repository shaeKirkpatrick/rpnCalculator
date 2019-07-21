package java.rpn;

import java.math.BigDecimal;
import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RealNumberStackTest {

    RealNumberStack stack = new RealNumberStack(15, 10);
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
}