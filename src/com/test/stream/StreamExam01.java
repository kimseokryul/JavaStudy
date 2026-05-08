package com.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    자바에서는 많은 양의 데이터를 저장하기 위해서
    배열이나 컬렉션(List, Map, Set, HashMap)을 사용하여 매번 새로운 코드를 작성해야함
    하지만, 이렇게 작성된 코드의 길이가 너무 길고 가독성이 떨어지고 코드의 재사용이 거의 불가능함
    즉, 데이터베이스의 쿼리와같이 정형화된 처리 패턴을 가지지 못했기에 데이터마다 다른 방법으로 접근해야만 했음
    이러한 문제점을 극복하기 위해서 자바 8버전부터 함수형 프로그래밍의 특성을 가진 stream API를 도입
    stream API는 데이터를 추상화하여 다루므로, 다양한 방식으로 저장된 데이터를 읽고 쓰기 위한 공통된 방법을 제공
    따라서, stream API를 이용하면 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방법으로 처리가 가능

    *** stream API의 특성
    1. 스트림은 외부 반복을 통해 작업하는 컬렉션과 달리 내부 반복(internal iteration)을 통해 작업을 수행
    2. 스트림은 재사용이 가능한 컬렉션과 달리 단 한 번만 사용
    3. 스트림은 원본 데이터를 변경하지 않음
    4. 스트림의 연산은 필터+맵(filter-map) 기반의 API를 사용하여 지연(lazy) 연산을 통해 성능을 최적화
     - 지연연산은 전부 수행하는 것이 아니라 수행이 필요한 것만 수행 --> 속도가 빠르다
     예) 전통적 방식 : 100개 필터링(100개 다함) --> 100번 변환,
         지연 연산 : 1번 필터 -> 변환, 2번 필터 -> 변환 --> 중간에라도 결정이 나면 종료
                    원래 100개 해야 하는데 5번 돌리니까 결과가 나왔다. 그럼 끝...
    5. 스트림은 병렬 처리를 지원

    
    스트림 API 예제
*/
public class StreamExam01 {

    public static void main(String[] args) {

        // *** stream API 동작 흐름
        // 데이터 소스 확보--> 스트림 생성 --> 중계 연산(필터) --> 중계 연산(맵) --> 최종연산(출력)

        // 스트림 API 예제
        System.out.println("<---- 1부터 10까지의 정수를 갖는 List에서 6보다작고, 짝수인 요소를 찾아 10배 시킨 리스트를 출력 ---> ");

        // 1. 데이터 소스 확보
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 2. 스트림 생성 --> 3. 중계연산
        list.stream().filter(i -> i < 6)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 10)
                .collect(Collectors.toList()).forEach(result -> System.out.println(result + " "));

        // 스트림 생성
        // 자바에서 제공하는 모든 컬렉션의 최고 상위 객체인 Collection 인터페이스에는 stream() 메소드가 정의되어 있음.
        // 따라서, Collection 인터페이스를 구현한 구현체인 List와 Set 컬렉션 클래스에서도 stream() 메소드를 생성할 수 있음.
        // 그리고, 배열을 다루는 Arrays 클래스에서도 다양한 형태의 stream() 메소드가 정의되어 있음
        // 또한, 기본 데이터 타입인 int, long, double 형을 다룰 수 있는 배열에 관한 스트림이 별도로 정의되어 있음
        // 이러한 스트림은 Java.util.stream 패키지의 IntStream, LongStream, DoubleStream 인터페이스로 각각
        // 제공

        System.out.println("\n <--- 리스트 객체로부터 스트림 생성 --->");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);

        Stream<Integer> stream = arrayList.stream(); // 컬렉션에서 스트림 생성
        stream.forEach(System.out::println); // forEach() 메소드를 이용한 스트림 요소의 순차 접근 및 출력

        System.out.println("<---배열로부터 스트림 생성--->");
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        IntStream stream1 = Arrays.stream(arr); // 배열을 스트림으로 전환
        stream1.forEach(System.out::println);

        System.out.println("<--- 가변 매개변수로부터 스트림 생성 --->");
        Stream<Double> stream2 = Stream.of(4.2, 2.5, 3.1, 1.5); // of 메소드의 가변 매개 변수로부터 값을 받아들임
        stream2.forEach(System.out::println);

        // 스트림 중계 연산
        // stream AP에 의해 생선된 초기 stream은 중계 연산을 통해 또 다른 stream으로 변환
        // 즉, 스트림 결과값을 연결하여 받고 이걸 다시 재가공해서 넘겨주는 방식으로 연결을 통한 데이터 연산이 가능
        // 스트림의 중계 연산은 필터-맵(filter-map) 기반의 API를 사용함으로서 지연(lazy) 연산을 통해 성능을 최적화

        // 1. 스트림 필터링 : filter(), distinct()
        // 2, 스트림 변환 : map(), flatMap() --> 중첩 stram 처리시 사용
        // 3. 스트림제한 : limit(), skip()
        // 4. 스트림 정렬 : sorted()
        // 5. 스트림 연산 결과 확인 : peek()

        System.out.println("<--- 스트림 필터링 예제 : 스트림에서 중복된 요소를 제거하고 홀수만을 골라내서 출력 --->");
        IntStream stream3 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 4, 5, 6, 7, 3, 2, 3); // 데이터 가공 및 스트림 생성
        stream3.distinct().filter(n -> n % 2 != 0).forEach(System.out::println);

        System.out.println("<--- 스트림 변환 예제 : 문자열로 이루어진 스트림을 각 문자열의 길이로 이루어진 스트림으로 변환 --->");
        Stream<String> stream4 = Stream.of("HTML", "CSS", "JAVA", "JAVASCRIPT");
        stream4.map(s -> s.length()).forEach((System.out::println));

        System.out.println("<--- 스트림 변환 예제 : 여러 문자열이 저장된 배열을 각 문자열에 포함된 단어로 이루어진 스트림으로 변환 --->");
        String[] arr1 = { "I study hard", "You study Java", "I am hungry" };
        Stream<String> stream5 = Arrays.stream(arr1);
        stream5.flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);

        System.out.println("<--- 스트림 제환 예제 --->");
        // limit() : 해당 스트림의 천번째 요소부터 전달된 개수만큼의 요소만으로 이루어진 새로운 스트림을 반환
        // skip() : 해당 스트림의 첫번째 요소부터 전달된 개수만큼의 요소를 제외한 나머지 요소만으로 이루어진 새로운 스트림을 반환함
        IntStream stream6 = IntStream.range(0, 10);
        IntStream stream7 = IntStream.range(0, 10);
        IntStream stream8 = IntStream.range(0, 10);

        stream6.limit(5).forEach(n -> System.out.println("limit() 사용 예제 : " + n + " "));
        stream7.skip(4).forEach(n -> System.out.println("skip() 사용 예제 : " + n + " "));
        stream8.skip(3).limit(5).forEach(n -> System.out.println("skip() + limit() 사용 예제 : " + n + " "));

        System.out.println("<--- 스트림 정렬 예제 --->");
        // 오름차순
        Stream<String> stream9 = Stream.of("park", "kim", "Lee", "Choi");
        stream9.sorted().forEach(n -> System.out.println("sort() 오름 차순 사용 예제 : " + n + " "));
        // 내림차순
        Stream<String> stream10 = Stream.of("park", "kim", "Lee", "Choi");
        stream10.sorted(Comparator.reverseOrder()).forEach(n -> System.out.println("sort() 내림 차순 사용 예제 : " + n + " "));

        System.out.println("<--- 스트림 디버그 예제 --->");
        //stream6.peek("스트림 생성").limit(5).peek("limit 메소드 작동").forEach(n -> System.out.println("limit() 사용 예제 : " + n + " "));

        //스트림 최종 연산
        // 1. 요소의 출력 : forEach()
        // 2. 요소의 소모 : reduce() --> reduce() 메소드는 첫번째와 두번째 ㅇ소를 가지고 연산을 수행한 뒤,
        //                 그 결과와 세번째 요소를 가지고 또 다시 연산을 수행, 이런식으로 해당 스트림의 모든 요소를 소모하여
        //                 연산을 수행하고, 그 결과를 반환
        // 3. 요소의 검색 : findFirst(), findAny()
        // 4. 요소의 검사 : anyMatch(), allMatch(), noneMatch()
        // 5. 요소의 통제 : count(), min(), max()
        // 6. 요소의 연산 : sum(), average()
        // 7. 요소의 수집 : collect() --> 스트림의 아이템들을 List 또는 Set 자료형으로 변환, 스트림의 아이템들을 joining,
        //                               스트림의 아이템들을 sorting하여 가장 큰 객체 리터, 스트림의 아이템들을 평균 값을 리턴


        System.out.println("<--- reduce 메소드를 이용하여 1부터 10까지의 합 구하기 예제 --->");
        Stream<Integer> numbers1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer> numbers2 = Stream.of(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> sum1 = numbers1.reduce((x,y)->x+y); //reduce method에서 초기값이 사용하지 않는 형태
        Optional<Integer> sum2 = Optional.ofNullable(numbers2.reduce(10,(x,y)->x+y));

        sum1.ifPresent(s-> System.out.println("sum1 : " + s));
        sum2.ifPresent(s -> System.out.println("sum2 : " + s));

        System.out.println("<--- collect 메소드를 이용하여 스트림 아이템을 합친 후 분리자 ,로 구분하기 예제 --->");
        Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach");
        //Object 객체는 모든 형태의 타입을 다룰 수 있는 최상위 Wrapper 객체
        //Object::toString --> 스트림으로 변환된 요소들 각각에 대해 Object 객체가 각각의 요소들 타입을 스트링으로 변환 
        //Object는 모든 종류의 타입을 다 손댈수가 있다. 그래서 스트링으로 변환된 요소들도 접근해서 스트링타입으로 변환이 가능
        //result는 문자열로 변환된 데이터 요소들을 합쳐서(banana,apple,mango,kiwi,peach) result에 전달
        
        System.out.println(fruits);
        String result = fruits.map(Object::toString).collect(Collectors.joining(", "));
        System.out.println(result);
    
    
    
    
    
    }
}
