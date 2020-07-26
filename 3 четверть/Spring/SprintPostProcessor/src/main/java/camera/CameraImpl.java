package camera;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("camera")
@Scope("prototype")
public class CameraImpl implements Camera {

    @Setter(onMethod = @__({@Autowired, @Qualifier("BlackCameraRoll")}))
    @Getter
    private CameraRoll cameraRoll;

    @Value("false")
    private boolean broken;

    public CameraImpl() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Доброго времени суток покупатель");
    }

    public void breaking() {
        this.broken = true;
    }

    public boolean isBroken() {
        return broken;
    }

    public void doPhotograph() {
        if (isBroken()) {
            System.out.println("Фотоаппарат сломан !");
            return;
        }
        System.out.println("Cделана фотография");
        cameraRoll.processing();
    }
}
