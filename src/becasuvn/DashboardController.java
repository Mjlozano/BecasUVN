/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCombination;
/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */
public class DashboardController implements Initializable {
   
    @FXML
      Menu debugmenu;
    
    @FXML
    private void openDebug(ActionEvent e) throws IOException {      
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Debug.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
            stage.setTitle("Debug");
            stage.setScene(new Scene(root));
            stage.show();
           
    }

    
    @FXML
    private void NuevaBeca(ActionEvent e) throws IOException {      
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BecaForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
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
