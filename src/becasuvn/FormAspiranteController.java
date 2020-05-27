/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
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
public class FormAspiranteController implements Initializable {

    @FXML
    JFXTextField nombretxt, num_doc, emailtxt;
    
    @FXML
    JFXComboBox t_doc, estrato, beca;
    
    @FXML
    JFXDatePicker f_nac;
    
    @FXML
    JFXListView req_info, benef_info;
    
    
    @FXML
    void aplicar(ActionEvent e){ //Para aplicar a la beca
        
    }
    
    @FXML
    void upload(ActionEvent e){ //Para subir archivos
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        req_info.setEditable(false);
        benef_info.setEditable(false);
    }    
    
}
