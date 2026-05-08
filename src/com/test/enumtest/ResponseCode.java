package com.test.enumtest;

public enum ResponseCode {

    // 1. 상수 선언 : (코드번호, 메세지, 성공 여부)
    // (일반적으로 상수는 대문자로)
    SUCCESS(200, "성공적으로 처리됨", true),
    BAD_REQUEST(400, "잘못된 요청임.", false),
    NOT_FOUND(404, "페이지를 찾을 수 없습니다", false),
    SERVER_ERROR(500, "서버 내부 오류 발생.", false);
    
    // 2. 인스턴스 변수 선언
    private final int code;
    private final String message;
    private final boolean isSuccess;

    // 3. 생성자
    ResponseCode(int code, String message, boolean isSuccess){
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    //4. getter
    public int getCode(){return this.code;}
    public String getMessage(){return this.message;}
    public boolean isSuccess(){return isSuccess;}

}
