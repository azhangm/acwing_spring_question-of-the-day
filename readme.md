# IOC 如何通过BeanFactory 实现原始版本的IOC容器？
在 Java 中，最简单的就是new 一个对象，IOC 容器，即 BeanFactory ，存在的意义就是将，创建对象和使用对象的业务代码解耦。
### 用原始的 IOC 容器管理一个Bean

> 原始的IOC容器非常简单，
> `XML reader` 从 外部 XML 文件中获取Bean的声明
> 
> 获取到Bean声明之后，需要使用反射，加载Bean Class 文件 创建实例
> 
> 创建实例之后、用 Map 保存 Bean 的实例
> 
> 最后 getBean 用 Map 获取 Bean 实例即可。

![img.png](img.png)


##### 没有其他属性的 Bean 定义 
```xml
<beans>
    <bean id='testService' class='nuc.zm.minis.test.TestServiceImpl'/>
</beans>
```
```java
public class BeanDefinition {
    private String id;
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
```
#### dom4j 灵活的读取xml文件的框架
[wiki地址](https://github.com/dom4j/dom4j/wiki/Quick-Start-Guide#powerful-navigation-with-xpath)

##### 读取配置文件 解析xml文本信息变为内存结构
```java
 private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL xmlUrl = getClass().getClassLoader().getResource(fileName);
        try {
            // parse xml
            Document document = saxReader.read(xmlUrl);
            Element rootElement = document.getRootElement();
            for (Element element : rootElement.elements()) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(id, className);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
```
##### 创建 bean 容器

```java
 private void createBeans(){
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String className = beanDefinition.getClassName();
            try {
                Object o = Class.forName(className).newInstance();
                beanMap.put(beanDefinition.getId(),o);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println("创建容器失败");
            }
        }
    }
```