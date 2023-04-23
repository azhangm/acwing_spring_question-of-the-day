package nuc.zm.springframework;

import java.util.HashMap;
import java.util.Map;

/**
 * bean工厂
 * @description 代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取。
 * @author zm
 * @date 2023/04/23
 */
public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
