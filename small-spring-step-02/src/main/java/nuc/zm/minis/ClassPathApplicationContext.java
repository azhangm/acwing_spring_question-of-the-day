package nuc.zm.minis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类路径应用程序上下文
 *
 * @author zm
 * @date 2023/06/05
 */
public class ClassPathApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String,Object> beanMap = new HashMap<>();

    public ClassPathApplicationContext(String fileName) {
        readXml(fileName);
        createBeans();
    }

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

    /**
     * 创建bean
     */
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


    public Object getBean(String beanName) {
        System.out.println(beanMap);
        return beanMap.get(beanName);
    }
}
