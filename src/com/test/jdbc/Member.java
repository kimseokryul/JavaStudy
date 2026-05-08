package com.test.jdbc;

public class Member {

    private String userid;
    private String username;
    private int age;

    // 기본 생성자
    Member() {
    }

    Member(String userid, String username, int age) {
        this.userid = userid;
        this.username = username;
        this.age = age;
    }

    public String getUserid() {
        return this.userid;
    }

    public String getUsername() {
        return this.username;
    }

    public int getAge() {
        return this.age;
    }

}
