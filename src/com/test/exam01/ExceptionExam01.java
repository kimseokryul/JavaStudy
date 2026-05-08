package com.test.exam01;

//3/0 --> 컴파일 이전에 IDE에서 런타임 오류가 발생될 수 있는 상황을 인식해서 예외 처리를 강제 시킴

//예외 VS 예외처리
//예외처리는 에러가 아님, 시스템 에러를 방지하기 위한 예방 절차

//예외 처리 방법 try-catch + finally

//예외 처리 최상위 클래스 Exception
public class ExceptionExam01 {

    public static void main(String[] args) {

        int c;
        // 일반적으로 많이쓰는 try-catch 예외처리 문법
        try {
            c = 4 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            c = -1;
        } finally {
            //예외가 발생하더라도 반드시 실행시켜야 할 명령
            System.out.println("무조건 실행");
        }
    }
}
