/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;
/**
 *
 * @author Jesus Lozano
 */
public class splashScreenController implements Initializable {
    
    @FXML
    private AnchorPane splash;
    
     ConexionMySQL session = new ConexionMySQL(); 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition transition = new FadeTransition(Duration.millis(2000),splash);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();
 
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage ventana = (Stage) splash.getScene().getWindow();
                ventana.hide();
                Connection conn = session.config("test", "root", "freischalten");
            if (conn != null){
                System.out.println("Sesion lista");
            }else{
                System.out.println("Credenciales Invalidas");
                
            }
                Stage ventanaApp = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                ventanaApp.setScene(scene);
                ventanaApp.setTitle("Login");
                ventanaApp.setResizable(false);
                ventanaApp.show();
            }
        });
    }    
    
}
