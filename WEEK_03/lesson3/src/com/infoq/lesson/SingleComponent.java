package com.infoq.lesson;

/**
 * 单节点
 */
public class SingleComponent extends Component{

    public SingleComponent(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println(this.name);
    }
}
