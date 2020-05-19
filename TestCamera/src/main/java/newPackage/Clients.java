package newPackage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Clients {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        // Получает фотоаппарат
        Camera camera = context.getBean("camera", Camera.class);

        // Ломает фотоаппарат
        camera.breaking();
        // Пытается сделать фото. Неудача!
        camera.doPhotograph();

        // Просит еще один фотоаппарат
        camera = context.getBean("camera", Camera.class);
        // Пытается сделать фото
        camera.doPhotograph();
    }
}