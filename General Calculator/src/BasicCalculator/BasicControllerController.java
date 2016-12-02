/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicCalculator;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.control.MenuItem;

/**
 * @version 1.0
 * @author Kalen
 */
public class BasicControllerController{
    private boolean shift = false;
    private boolean calculated = false;
    
    Scene scene = general.calculator.GeneralCalculator.scene;
    Stage stage = general.calculator.GeneralCalculator.stage;
    
    
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, zero, equals, backspace,
                   plus, minus, multiply, divide, revert, clear, clearCurrent, decimal;
    
    @FXML
    private MenuItem chgHeight, chgTemp;
    
    @FXML
    private TextField output;
    
    @FXML
    private void append(ActionEvent event){
        String buttonText = ((Button) event.getSource()).getText();
        
        if(buttonText.matches("[1]|[2]|[3]|[4]|[5]|[6]|[7]|[8]|[9]|[0]|[.]|")){
            output.appendText(buttonText);
        }else{
            output.appendText(buttonText);
        }
    }  
    
    @FXML
    private void genericButton(ActionEvent event){
        if(event.getSource() == equals)
            calcResults();
        else if(event.getSource() == backspace)
            removeLast();
    }
    
    @FXML
    private void checkKey(KeyEvent event){
        if (null != event.getCode()) switch (event.getCode()) {
            case SHIFT:
                shift = true;
                break;
            case BACK_SPACE:
                removeLast();
                break;
            case DIGIT1:
                updateOutput(1);
                break;
            case NUMPAD1:
                updateOutput(1);
                break;
            case DIGIT2:
                updateOutput(2);
                break;
            case NUMPAD2:
                updateOutput(2);
                break;
            case DIGIT3:
                updateOutput(3);
                break;
            case NUMPAD3:
                updateOutput(3);
                break;
            case NUMPAD4:
                updateOutput(4);
                break;
            case DIGIT4:
                updateOutput(4);
                break;
            case DIGIT5:
                updateOutput(5);
                break;
            case NUMPAD5:
                updateOutput(5);
                break;
            case DIGIT6:
                updateOutput(6);
                break;
            case NUMPAD6:
                updateOutput(6);
                break;
            case DIGIT7:
                updateOutput(7);
                break;
            case NUMPAD7:
                updateOutput(7);
                break;
            case DIGIT8:
                updateOutput(8);
                break;
            case NUMPAD8:
                updateOutput(8);
                break;
            case DIGIT9:
                updateOutput(9);
                break;
            case NUMPAD9:
                updateOutput(9);
                break;
            case DIGIT0:
                updateOutput(0);
                break;
            case NUMPAD0:
                updateOutput(0);
                break;
            case PLUS:
                output.appendText("+");
                break;
            case ADD:
                output.appendText("+");
                break;
            case MINUS:
                output.appendText("-");
                break;
            case SUBTRACT:
                output.appendText("-");
                break;
            case SLASH:
                output.appendText("/");
                break;
            case DIVIDE:
                output.appendText("/");
                break;
            case MULTIPLY:
                output.appendText("*");
                break;
            case EQUALS:
                if(shift)
                    output.appendText("+");
                else
                    calcResults();
                break;
            case PERIOD:
                output.appendText(".");
                break;
            case DELETE:
                output.setText("");
                break;
            case ENTER:
                calcResults();
                break;
            default:
                break;
        }
    }
    
    @FXML
    private void checkReleased(KeyEvent event){
       if(event.getCode() == KeyCode.SHIFT)
           shift = false;
       
    }
    
    @FXML
    private void clearAll(ActionEvent event){
        output.setText("");
        calculated = false;
    }
    
    @FXML
    private void exit(ActionEvent event){
        System.exit(0);
    }
    
    private void calcResults(){
        String equation = output.getText();
        
        String[] sNums = equation.split("[+]|[-]|[/]|[*]");
        ArrayList<Double> nums = new ArrayList<>(sNums.length);
        String[] operations = new String[equation.length()];
        
        double finalCalc = 0.0;
        
        int numIterator = 0;
        
        for(String sNum : sNums){
            nums.add(Double.parseDouble(sNum));
        }
        
        numIterator = 0;
        
        for(int i = 0 ; i < equation.length() ; i++){
            try{
                //If this throws an error, it must be an operation.
                Double.parseDouble(""+equation.charAt(i));
            }catch(Exception e){
                if(equation.charAt(i) != '.'){
                    operations[i] = ""+equation.charAt(i);
                
                    switch(operations[i]){
                        case "+":
                            finalCalc = nums.get(numIterator++) + nums.get(numIterator++);
                            nums.set(--numIterator, finalCalc);
                            break;
                        case "-":
                            finalCalc = nums.get(numIterator++) - nums.get(numIterator++);
                            nums.set(--numIterator, finalCalc);
                            break;
                        case "/":
                            finalCalc = nums.get(numIterator++) / nums.get(numIterator++);
                            nums.set(--numIterator, finalCalc);
                            break;
                        case "*":
                            finalCalc = nums.get(numIterator++) * nums.get(numIterator++);
                            nums.set(--numIterator, finalCalc);
                            break;
                        default:
                            finalCalc = nums.get(numIterator);
                    }
                }
            }
        }
        
        output.setText((finalCalc % 1 == 0 ? (int) finalCalc+"" : finalCalc+""));
        calculated = true;
    }
    
    private void removeLast(){
        output.setText(output.getText().substring(0,output.getText().length()-1));
    }
    
    private void updateOutput(int i){
        
            if(i == 8)
                output.appendText(shift ? "*" : ""+i);
            else
                output.appendText(""+i);
        
    }
    
    @FXML
    private void chg(ActionEvent event) throws IOException{
        Parent root = null;
        if(event.getSource() == chgHeight)
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\Height\\Height.FXML"));
        else if(event.getSource() == chgTemp)
            root = FXMLLoader.load(new URL("file:\\C:\\Program Files (x86)\\General Calculator\\General Calculator\\src\\Temperature\\Temperature.FXML"));
        
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void chgMeasurement(){
       // general.calculator.GeneralCalculator.s
    }
}