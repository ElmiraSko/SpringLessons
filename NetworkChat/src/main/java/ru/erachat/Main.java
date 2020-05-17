package ru.erachat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

//        При помощи xml файла - spring-context.xml
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

//        При помощи  java-конфигурации - AppConfig.class
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

        Server server = context.getBean(Server.class);
        server.starting();
    }
}
