import camera.Camera;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(config.AppConfig.class);
        Camera camera = context.getBean("camera",Camera.class);
        System.out.println("Покупатель ломает камеру");
        camera.breaking();
        System.out.println("Покупатель пробуем сделать фото");
        camera.doPhotograph();
        System.out.println();
        System.out.println("Покупатель просит новый фотоаппарат");
        camera = context.getBean("camera",Camera.class);
        camera.doPhotograph();
    }
}
