/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */
public class HomeController implements Initializable {

    @FXML
    JFXTextField usertxt;
    
    @FXML
    JFXPasswordField passwtxt;
    
    boolean VerifyUser(){
        if(usertxt.getText().isEmpty() || passwtxt.getText().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    @FXML
    void logIn(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
