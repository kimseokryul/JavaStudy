package com.test.exam01;

class OutClass {

    private int num = 10;
    private static int sNum = 20;

    private InClass inClass;

    public OutClass() {
        inClass = new InClass();

    }

    class InClass {
        int inNum =100;

        void inTest() {
            System.out.println("외부 클래스의 인스턴스 변수 : " + num);
            System.out.println("내부 클래스의 정적 변수 : " + sNum);
        }
    }

    public void usingClass() {
        inClass.inTest();
    }
}

public class InnerClassTest {
    public static void main(String[] args) {
        
        OutClass outClass = new OutClass();

        OutClass.InClass inClass = outClass.new InClass();
        inClass.inTest();
        outClass.usingClass();

        System.out.println("inClass.inNum = " + inClass.inNum);
    }
}
