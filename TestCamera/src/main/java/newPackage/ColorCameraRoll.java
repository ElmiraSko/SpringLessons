package newPackage;

import org.springframework.stereotype.Component;

//@Component("cameraRoll")
public class ColorCameraRoll implements CameraRoll {

    public void processing() {
        System.out.println("- 1 цветной кадр");

    }
}


