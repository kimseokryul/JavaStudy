package com.test.exam01;

public class UserRegistry {
    private String name;
    private int age;
    public UserRegistry(){}
    public UserRegistry(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("회원가입 성공");
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public void setName(String name){
        this.name =name;
    }
    public void setAge(int age){
        this.age = age;
    }
}

class UserRegistryMain {

    public static void main(String[] args) {
        
        UserRegistry userRegistry = new UserRegistry("KIM", 31);
        System.out.println("고객의 이름 : " + userRegistry.getName());
        System.out.println("고객의 나이 : " + userRegistry.getAge());

        userRegistry.setName("Park");
        userRegistry.setAge(50);
        System.out.println("수정 고객의 이름 : " + userRegistry.getName());
        System.out.println("수정 고객의 나이 : " + userRegistry.getAge());

    }

}