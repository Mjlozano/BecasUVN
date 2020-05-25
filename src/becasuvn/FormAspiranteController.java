/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
public class FormAspiranteController implements Initializable {

    @FXML
    JFXTextField nombretxt, num_doc;
    
    @FXML
    JFXComboBox t_doc, estrato, beca;
    
    @FXML
    JFXDatePicker f_nac;
    
    @FXML
    JFXTextArea req_info, benef_info;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        req_info.setEditable(false);
        benef_info.setEditable(false);
    }    
    
}
