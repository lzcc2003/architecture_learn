package com.joyce.plugin;

public class Singleton4 {

    private Singleton4(){

    }

    private static class Singleton4Holder{
        private static Singleton4 singleton4 = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return Singleton4Holder.singleton4;
    }
}
