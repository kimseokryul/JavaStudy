package com.test.enumtest;
// enum(열거형) -> enum 클래스라는 것을 나타내는 예약어(지시어)
// 특저 상수들을 그룹화해서 관리흫 원활하게 하기 위해
// 순수 문법 이해하면서 구조 암기
public enum Day {

    //생성자를 통한 데이터 매핑
    //상수 목록을 나열
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");

    //인스턴스 변수 --> 상수
    private final String label;

    //생성자
    Day(String label) {
        this.label = label;
    }

    //getter 메소드
    public String getLabel() {
        return label;
    }

}
