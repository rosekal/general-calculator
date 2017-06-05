package Temperature;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Kalen
 */
public class TemperatureCalculator{
    Scene scene = general.calculator.GeneralCalculator.scene;
    Stage stage = general.calculator.GeneralCalculator.stage;
    
    DecimalFormat df = new DecimalFormat("##.00");
    
    @FXML
    private TextField celsius, fahrenheit, kelvin;
    
    @FXML
    private MenuItem chgBasic, chgHeight;
    
    @FXML
    private void celsiusChange(KeyEvent ke){
        errorCheck(celsius);
        
        if(!celsius.getText().equals("")){
            fahrenheit.setText(df.format(Double.parseDouble(celsius.getText()) * 1.8 + 32));
            kelvin.setText(df.format(Double.parseDouble(celsius.getText()) + 273.15));
        }
    }
    
    @FXML
    private void fahrenheitChange(KeyEvent ke){
        errorCheck(fahrenheit);
        
        if(!fahrenheit.getText().equals("")){
            celsius.setText(df.format((Double.parseDouble(fahrenheit.getText()) - 32) / 1.8));
            kelvin.setText(df.format((Double.parseDouble(fahrenheit.getText()) + 459.67) * (5.0/9.0)));
        }
    }
    
    @FXML
    private void kelvinChange(KeyEvent ke){
        errorCheck(kelvin);
    }
    
    @FXML
    private void keyPressed(KeyEvent ke){
        
    }
    
    @FXML
    private void chg(ActionEvent event) throws IOException{
        Parent root = null;
        if(event.getSource() == chgBasic){
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\BasicCalculator\\BasicCalculator.FXML"));
        }else if(event.getSource() == chgHeight){
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\Height\\Height.FXML"));
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void errorCheck(TextField tf) {
        boolean error;
        do{
            String text = tf.getText();

            try{
                
                if(!text.equals(""))
                    Double.parseDouble(text);
                error = false;
            }catch(Exception e){
                tf.setText(text.substring(0, text.length() - 1));
                tf.end();
                error = true;
            }
        }while(error);
        
        if(tf.getText().matches("[0-9]*[.]{1}[0-9]{3,}")){
            tf.setText(df.format(Double.parseDouble(tf.getText())));
            tf.end();
        }
    }
}