package nuc.zm.springframework;

import org.junit.Test;

/**
 * 方法接口测试
 *
 * @author zm
 * @date 2023/04/23
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 初始化
        BeanFactory beanFactory = new BeanFactory();

        // 注册 bean 到容器中
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 拿到 bean
        Object userService = beanFactory.getBean("userService");
        UserService bean = (UserService) userService;
        // 使用 bean 方法
        bean.queryInfo();
    }
}
