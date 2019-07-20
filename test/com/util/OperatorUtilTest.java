package com.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorUtilTest {

    @Test
    void isOperator() {
        assertTrue(OperatorUtil.isOperator("+"));
        assertTrue(OperatorUtil.isOperator("-"));
        assertTrue(OperatorUtil.isOperator("/"));
        assertTrue(OperatorUtil.isOperator("*"));
        assertTrue(OperatorUtil.isOperator("sqrt"));
        assertFalse(OperatorUtil.isOperator("undo"));
        assertFalse(OperatorUtil.isOperator("clear"));
        assertFalse(OperatorUtil.isOperator("1"));
        assertFalse(OperatorUtil.isOperator("10"));
    }

    @Test
    void reverseOperator() {
        assertEquals("-", OperatorUtil.reverseOperator("+"));
        assertEquals("+", OperatorUtil.reverseOperator("-"));
        assertEquals("/", OperatorUtil.reverseOperator("*"));
        assertEquals("*", OperatorUtil.reverseOperator("/"));
    }

    @Test
    void isSqrt() {
        assertTrue(OperatorUtil.isSqrt("sqrt"));
        assertFalse(OperatorUtil.isSqrt("*"));
    }
}