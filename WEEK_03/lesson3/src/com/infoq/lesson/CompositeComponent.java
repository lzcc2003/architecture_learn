package com.infoq.lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * 复杂对象，组合关系
 */
public class CompositeComponent extends Component {
    private List<Component> componentList = new ArrayList<>();

    public CompositeComponent(String name) {
        super(name);
    }

    /**
     * 添加组件
     *
     * @param component
     */
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void display() {
        System.out.println(this.name);
        componentList.forEach(component -> component.display());
    }

    public static void main(String[] args) {
        CompositeComponent winFormComponent = new CompositeComponent("WINDOW窗口");
        winFormComponent.add(new SingleComponent("LOGO图片"));
        winFormComponent.add(new SingleComponent("登录"));
        winFormComponent.add(new SingleComponent("注册"));
        CompositeComponent frameComponent = new CompositeComponent("FRAME1");
        frameComponent.add(new SingleComponent("用户名"));
        frameComponent.add(new SingleComponent("文本框"));
        frameComponent.add(new SingleComponent("密码"));
        frameComponent.add(new SingleComponent("密码框"));
        frameComponent.add(new SingleComponent("复选框"));
        frameComponent.add(new SingleComponent("记住用户名"));
        frameComponent.add(new SingleComponent("忘记密码"));
        winFormComponent.add(frameComponent);
        winFormComponent.display();

    }
}
