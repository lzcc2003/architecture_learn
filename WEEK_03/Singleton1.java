package com.joyce.plugin;

public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){

    }

    /**
     * 饿汉模式
     * @return
     */
    public static Singleton1 getInstance(){
        return singleton1;
    }
}
