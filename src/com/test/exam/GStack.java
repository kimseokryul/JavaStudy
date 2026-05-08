package com.test.exam;

class GStack<T> {

    int tos;
    Object[] stack;

    public GStack() {
        tos = 0;
        stack = new Object[10];
    }

    public void push(T item) {
        if (tos == 10)
            return;
        stack[tos] = item;
        tos++;
    }

    public T pop() {
        if (tos == 0)
            return null;
        tos--;
        return (T) stack[tos];
    }
}
