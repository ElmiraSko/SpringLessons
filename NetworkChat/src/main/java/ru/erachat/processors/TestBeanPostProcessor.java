package ru.erachat.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
       public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
           return bean;
       }
       public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
           return bean;
       }
}


