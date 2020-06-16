package com.infoq.lesson;

/**
 * 抽象类
 */
public abstract class Component {

    protected String name;

    protected Component(String name){
        this.name = name;
    }

    public abstract void display();
}
