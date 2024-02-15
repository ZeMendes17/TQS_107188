package pt.ua.deti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

public class TqsStackTest {
    TqsStack<Integer> numStack;

    @BeforeEach
    void setup() {
        numStack = new TqsStack<Integer>();
    }

    @Test
    @DisplayName("A stack is empty on construction")
    void testIsEmpty() {
        Assertions.assertTrue(numStack.isEmpty());
    }

    @Test
    @DisplayName("A stack has size 0 on construction")
    void testSize() {
        Assertions.assertEquals(0, numStack.size());
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and the size is n")
    void testCorrectSize() {
        // add n elements to the stack (n = 5 in this case)
        numStack.push(3);
        numStack.push(1);
        numStack.push(24);
        numStack.push(6);
        numStack.push(12);

        // Stack cannot be empty
        Assertions.assertFalse(numStack.isEmpty());
        // Stack size is n
        Assertions.assertEquals(5, numStack.size());
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x")
    void testPop() {
        int element = 4;
        numStack.push(element);

        Assertions.assertEquals(element, numStack.pop());
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    void testPeek() {
        int element = 13;
        numStack.push(element);

        int sizeBefore = numStack.size();

        // the value returned from peek() should be the same
        Assertions.assertEquals(element, numStack.peek());
        // the size of the stack should stay the same
        Assertions.assertEquals(sizeBefore, numStack.size());
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void testSizeAfterPops() {
        // add n elements to the stack (n = 3 in this case)
        numStack.push(37);
        numStack.push(11);
        numStack.push(2);

        // pop n elements from the stack
        numStack.pop();
        numStack.pop();
        numStack.pop();

        // Stack should be empty
        Assertions.assertTrue(numStack.isEmpty());
        // Stack size should be 0
        Assertions.assertEquals(0, numStack.size());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    void testPopException() {
        // Stack is empty
        Assertions.assertTrue(numStack.isEmpty());
        // Pop from an empty stack should throw a NoSuchElementException
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> {
            numStack.pop();
        });
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    void testPeekException() {
        // Stack is empty
        Assertions.assertTrue(numStack.isEmpty());
        // Peek into an empty stack should throw a NoSuchElementException
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> {
            numStack.peek();
        });
    }

    @Test
    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    void testPushException() {
        // create a bounded stack with a bound of 3
        TqsStack<Integer> boundedStack = new TqsStack<Integer>(3);

        // add 3 elements to the stack
        boundedStack.push(1);
        boundedStack.push(2);
        boundedStack.push(3);

        // Stack is full, should throw an IllegalStateException
        Assertions.assertThrows(IllegalStateException.class, () -> {
            boundedStack.push(4);
        });
    }

    @AfterEach
    void tearDown() {
        numStack = null;
    }
}