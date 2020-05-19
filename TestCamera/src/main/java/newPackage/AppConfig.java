package newPackage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "newPackage")
public class AppConfig {

//    @Bean(name="cameraRoll")
//    public CameraRoll cameraRoll() {
//        return new ColorCameraRoll() ;
//    }
//
//    @Bean(name="camera")
//    @Scope("prototype")
//    public Camera camera(CameraRoll cameraRoll){
//        CameraImpl camera = new CameraImpl();
//        camera.setCameraRoll(cameraRoll);
//        return camera;
//    }

}
