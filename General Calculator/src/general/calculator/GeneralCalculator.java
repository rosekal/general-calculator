package general.calculator;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeneralCalculator extends Application {
    public static Stage stage;
    public static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        GeneralCalculator.stage = stage;
        Parent root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\BasicCalculator\\BasicCalculator.FXML"));
        
        scene = new Scene(root);
        
        GeneralCalculator.stage.setScene(scene);
        GeneralCalculator.stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}