package camera;

import org.springframework.stereotype.Component;

@Component("ColorCameraRoll")
public class ColorCameraRoll implements CameraRoll {
    public void processing() {
        System.out.println("-1 цветной кадр");
    }
}
