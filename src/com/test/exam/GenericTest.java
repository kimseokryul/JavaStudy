package com.test.exam;

public class GenericTest {

    public static void main(String[] args) {
        GStack<String> stringStack = new GStack<String>();

        stringStack.push("seoul");
        stringStack.push("busan");
        stringStack.push("LA");

        for (int i = 0; i < 3; i++)
            System.out.println(stringStack.pop());
    }
}
