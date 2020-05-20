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
import java.sql.Connection;

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
    
    ConexionMySQL session = new ConexionMySQL(); 
    
    boolean VerifyUser(){
        if(usertxt.getText().isEmpty() || passwtxt.getText().isEmpty()){
            System.out.println("Campos vacios");
            return false;
        }else{
            System.out.println("We good to go");
            return true;
        }
    }
    
    @FXML
    void logIn(){
        if (this.VerifyUser()){
            /*Connection conn = session.config("test", usertxt.getText(), passwtxt.getText());
            if (conn != null){
                System.out.println("Sesion lista");
            }else{
                System.out.println("Credenciales Invalidas");
                
            }*/
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
