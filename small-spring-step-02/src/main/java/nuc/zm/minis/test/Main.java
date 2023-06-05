package nuc.zm.minis.test;

import nuc.zm.minis.ClassPathApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathApplicationContext classPathApplicationContext = new ClassPathApplicationContext("beans.xml");
        TestService testService = (TestService) classPathApplicationContext.getBean("testService");
        testService.hello();
    }
}
