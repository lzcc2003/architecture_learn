package com.joyce.plugin;

public class Singleton2 {

    private static Singleton2 singleton2;

    private Singleton2(){

    }

    /**
     * 懒汉模式，同步锁
     * @return
     */
    public synchronized Singleton2 getInstance(){
        if(singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
