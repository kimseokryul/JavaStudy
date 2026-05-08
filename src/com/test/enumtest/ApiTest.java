package com.test.enumtest;

public class ApiTest {

    public static void main(String[] args) {
        
        ResponseCode status = ResponseCode.NOT_FOUND;

        System.out.println("결과 코드 : " + status.getCode());
        System.out.println("메세지 : " + status.getMessage());

        if(!status.isSuccess()) {
            System.out.println("경고 : 요청 실패");
        }
    }
}
