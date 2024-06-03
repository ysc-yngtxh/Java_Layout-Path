package com.example;

/**
 * Hello world!
 */
public class App{

    public int add(int n1, int n2){
        return n1+n2;
    }

    public static void main(String[] args) {
        App hello = new App();
        int res = hello.add(10, 20);
        System.out.println("10+20=" + res);
    }
}
