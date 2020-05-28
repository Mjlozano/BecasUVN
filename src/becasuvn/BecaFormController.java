/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */

/*I would go to you if I could fly
   so 'll grip my seat and close my eyes
   There are things that feel to big to send along in letters to the clouds
    I don't need to want what I don't have
    Getting trapped in stories at my hands
    Promising me a future that was never mine and was taken from someone else

*/
public class BecaFormController implements Initializable {

    @FXML
    JFXTextField nombreBecatxt, n_cupostxt;
    @FXML
    JFXDatePicker f_iniciotxt, f_finaltxt;
    @FXML
    JFXTextField beneficiostxt, requisitostxt, doctxt;
    @FXML
    JFXListView<String> list_benef, list_req, list_doc;
    
    
    @FXML
    void crearBeca(ActionEvent e){    //Metodo para insertar una beca
        String nBeca = nombreBecatxt.getText();
        String n_cupos = n_cupostxt.getText();
        LocalDate f_inicio = f_iniciotxt.getValue();
        LocalDate f_final = f_finaltxt.getValue();
        List<String> ben_list = list_benef.getItems();
        List<String> req_list = list_req.getItems();
        List<String> doc_list = list_doc.getItems();
        System.out.println(f_inicio);
    }
    
    @FXML
    void addDoc(ActionEvent e){  //Metodo para añadir un documento
        String tmp = doctxt.getText();
        list_doc.getItems().add(tmp);
        doctxt.setText("");
    }
    
    @FXML
    void addReq(ActionEvent e){  //Metodo para añadir un requisito
        String tmp = requisitostxt.getText();
        list_req.getItems().add(tmp);
        requisitostxt.setText("");
    }
    
    @FXML
    void addBenef(ActionEvent e){  //Metodo para añadir un beneficio
         String tmp = beneficiostxt.getText();
        list_benef.getItems().add(tmp);
        beneficiostxt.setText("");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
