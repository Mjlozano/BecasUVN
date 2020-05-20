/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    void logIn(ActionEvent e) throws IOException{
        if (this.VerifyUser()){
            /*Connection conn = session.config("test", usertxt.getText(), passwtxt.getText());
            if (conn != null){
                System.out.println("Sesion lista");
            }else{
                System.out.println("Credenciales Invalidas");
                
            }*/
        }
       go(e); 
       
    }
    
    @FXML
    private void go(ActionEvent e) throws IOException {      
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Becas UVN");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
