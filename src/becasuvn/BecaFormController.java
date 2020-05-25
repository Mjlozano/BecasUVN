/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */
public class BecaFormController implements Initializable {

    @FXML
    JFXTextField nombreBecatxt, doctxt;
    @FXML
    JFXDatePicker f_iniciotxt, f_finaltxt;
    @FXML
    JFXTextArea beneficiostxt, requisitostxt;
    
    
    @FXML
    void crearBeca(ActionEvent e){    //Metodo para insertar una beca
        
    }
    
    @FXML
    void addDoc(ActionEvent e){  //Metodo para añadir un documento
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
