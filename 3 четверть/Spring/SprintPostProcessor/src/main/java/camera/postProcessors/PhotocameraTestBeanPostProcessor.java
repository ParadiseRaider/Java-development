package camera.postProcessors;

import camera.Camera;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PhotocameraTestBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Camera) {
            System.out.println("Делаю пробное фото");
            ((Camera) bean).doPhotograph();
            System.out.println("Отлично все работает");
        }
        return bean;
    }
}
