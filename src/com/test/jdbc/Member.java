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

    static class Builder {
        private String userid;
        private String username;
        private int age;

        public Builder userid(String userid){
            this.userid = userid;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Member build(){
            if(userid == null || username == null || age == 0)
                throw new IllegalStateException("Cannot create Member");
            return new Member(userid, username, age);
        }
    }
}
