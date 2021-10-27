package com.ws.soon.test;

import com.ws.soon.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsTest {

    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        // 获取Kie容器对象（默认容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从Kie容器对象中获取会话对象（默认session对象
        KieSession kieSession = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(160d);

        // 将order对象插入工作内存
        kieSession.insert(order);

        System.out.println("匹配规则前优惠后价格：" + order.getRealPrice());

        // 匹配对象
        // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
        kieSession.fireAllRules();

        // 关闭会话
        kieSession.dispose();

        System.out.println("优惠前价格：" + order.getOriginalPrice() + "\n优惠后价格：" + order.getRealPrice());
    }
}
