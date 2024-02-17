package pt.ua.deti;

import java.util.LinkedList;

public class TqsStack<T> {
    // Stack Data Structure Implementation
    private LinkedList<T> stack;
    private int bound = -1;

    public TqsStack() {
        stack = new LinkedList<T>();
    }

    public TqsStack(int bound) {
        stack = new LinkedList<T>();
        this.bound = bound;
    }

    public T pop() {
        // if the Stack is empty, throw a NoSuchElementException
        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return stack.pop();
    }

    public int size() {
        return stack.size();
    }

    public T peek() {
        // if the Stack is empty, throw a NoSuchElementException
        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return stack.peek();
    }

    public void push(T element) {
        // if the Stack is full, throw a IllegalStateException
        if (bound != -1 && stack.size() == bound) {
            throw new IllegalStateException();
        }
        stack.push(element);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
