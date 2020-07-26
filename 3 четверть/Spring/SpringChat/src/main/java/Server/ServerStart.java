package Server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStart {
    public static void main(String[] args) {
        ApplicationContext contextJavaConfig = new AnnotationConfigApplicationContext(Server.ServerConfig.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    }
}
