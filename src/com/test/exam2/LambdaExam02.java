package com.test.exam2;

import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface MyLambdaFuction {

    int sum(int a, int b);
}

@FunctionalInterface
interface MyPrint {

    String printLambda();
}

public class LambdaExam02 {
    public static void main(String[] args) {
        // 람다표현식을 이용하여 위의 인터페이스에서 구성한 익명함수를 정의 -> 2개의 인자를 받아서 메소드에서 계산 후 반환
        MyLambdaFuction lambdaFuction = (int a, int b) -> a + b;
        System.out.println(lambdaFuction.sum(5, 4));

        MyPrint myPrint = () -> "Hello World!";
        System.out.println("람다표현식을 이용하여 인자없이 문자열을 반환하는 예 = " + myPrint.printLambda());

        // Supplier : 매개변수 없이 반환값 만을 갖는 함수형 인터페이스에서
        Supplier<String> supplier = () -> "Hello My Lambda World"; // 함수 선언 및 정의
        System.out.println("Supplier :  매개변수 없이 반환값 만을 갖는 함수형 인터페이스 예시 = " + supplier.get());

        // function : function<T,R> --> 매개변수 T를 받아서 연산 후, 결과 R을 반환. 실행 메소드 : apply()
        Function<String, Integer> function = str -> str.length();
        System.out.println("Function : 매개변수 T를 받아서 연산 후, 결과 R을 반환하는 함수형 인터페이스 사용 예시 = " + function.apply("hello world"));

        //Consumer : Consumer<T>--> 매개변수 T를 받아서 소비(사용)만 하고 반환하는 값이 없는 함수형 인터페이스. 실행메소드 : accept()
        //(str.split(" ")[0])-> 스페이스를 "분리자"로 하여 문자열을 분리하고 배열에 저장 후 배열의 첫번째 요소에 해당되는 값을 리턴
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
        consumer.accept("Hello World");
        //Chainnig 기법. andThen이 이 역할을 수정
        // 1. accept 메소드를 통해 인자를 받고 명령을 사용(소비) --> 실행
        // 2. accept에서 받은 인자를 가지고 실행할 명려으로 넘겨주고 
        consumer.andThen(s->System.out.println("Consumer:매개변수 T를 받아서 소비(사용)만 하고 반환하는 값이 없는 함수형 인터페이스"))
                .accept("Hello wonderful world!!!");

        //Predicate : Predicate<T> --> 매개변수 T를 받아 처리한 후 Boolean을 반환하는 함수형 인터페이스. 실행 메소드 : test()
        Predicate<String> predicate = (s) -> s.equals("Helli world");
        System.out.println("Predicate : 매개변수 T를 받아 처리한 후 Boolean을 반환하는 함수형 인터페이스 사용 예 = " + predicate.test("Hello World10")); 
    
        //메소드 참조 : 람다표현식이 단순히 기존에 있는 메소드를 호출하기만 할때 사용하는 방식으로, 매개변수 전달 과정을 생략
        //클래스이름::메소드이름 또는 인스턴스변수이름::메소드이름 --> 이렇게 표현
        Function<String,Integer> func1 = str -> str.length(); //람다표현식
        System.out.println(func1.apply("Hello World5 !!!!!!"));

        Function<String, Integer> func2 = String::length; //메소드 참조 표현식 
        System.out.println(func1.apply("Hello World5 !!!!!!"));

        //DoubleUnaryOperator 인터페이스는 한개의 double형 매개변수를 전발 받아 한개의 double형 값은 반환
        //java.util.function 패키지에서 제공하는 함수형 인터페이스
        DoubleUnaryOperator oper;
        oper = (n) -> Math.abs(n); //람다표현식
        System.out.println(oper.applyAsDouble(-5));

        oper = Math::abs; //메소드 참조 표현식
        System.out.println(oper.applyAsDouble(-5));

        //생성자 참조
        Function<Integer, double[]> func4 = a -> new double[a]; //람다표현식
        Function<Integer, double[]> func5 = double[]::new; //생성자 참조표현 
    }
}
