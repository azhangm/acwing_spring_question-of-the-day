package nuc.zm.springframework;

/**
 * bean定义
 * @description : 用于定义 Bean 实例化信息，现在的实现是以一个 Object 存放对象
 * @author zm
 * @date 2023/04/23
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
