package Data;

import java.io.IOException;
import java.net.URL;
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
public class DataController{
    Scene scene = general.calculator.GeneralCalculator.scene;
    Stage stage = general.calculator.GeneralCalculator.stage;
    
    private boolean shift = false;
    
    @FXML
    private TextField feet, inches, cms;
    
    @FXML
    private MenuItem chgBasic, chgTemp;
    
    @FXML
    private void feetChange(KeyEvent ke){
        checkInput(ke, feet);
        calculateCms(feet, inches);
    }
    
    @FXML
    private void inchChange(KeyEvent ke){
        checkInput(ke, inches);
        calculateCms(inches, feet);
    }
    
    @FXML
    private void centChange(KeyEvent ke){
        checkInput(ke, cms);
        
        double dInches = 0;
        
        try{
            dInches = Double.parseDouble(cms.getText()) / 2.54;
        }catch(Exception e){}
        
        feet.setText((int) dInches / 12 +"");
        inches.setText(dInches % 12 +"");
    }
    
    @FXML
    private void keyPressed(KeyEvent ke){
        if(ke.getCode() == KeyCode.SHIFT)
            shift = true;
    }
    
    @FXML
    private void keyReleased(KeyEvent ke){
        if(ke.getCode() == KeyCode.SHIFT)
            shift = false;
    }
    
    @FXML
    private void chg(ActionEvent event) throws IOException{
        Parent root = null;
        if(event.getSource() == chgBasic){
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\BasicCalculator\\BasicCalculator.FXML"));
        }else if(event.getSource() == chgTemp){
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\Temperature\\Temperature.FXML"));
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void checkInput(KeyEvent ke, TextField tf) {
        try{
            for(char c : tf.getText().toCharArray())
                if(!Character.isDigit(c) && c != '.' || shift){
                    feet.setText(feet.getText().substring(0, feet.getText().length() - 1));
                    feet.end();
                }
        }catch(Exception e){}
    }

    private void calculateCms(TextField tf1, TextField tf2) {
        double dNum2, dNum1 = 0;
        
        try{
            dNum1 = Double.parseDouble(tf1.getText());
        }catch(Exception e){}
        
        try{
            dNum2 = Double.parseDouble(tf2.getText());
        }catch(Exception e){
            tf2.setText(0+"");
            dNum2 = 0;
        }
        
        cms.setText((dNum2 * 12 + dNum1) * 2.54 +"");
    }
}