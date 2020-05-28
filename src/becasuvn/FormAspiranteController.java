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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */
public class FormAspiranteController implements Initializable {

    ConexionMySQL SQL;
    Connection conn;
    Statement st = null;
    ResultSet rs = null;

    @FXML
    JFXTextField nombretxt, num_doc, emailtxt, direccion;

    @FXML
    JFXComboBox t_doc, estrato, beca;

    @FXML
    JFXDatePicker f_nac;

    @FXML
    JFXListView req_info, benef_info;

    @FXML
    void aplicar(ActionEvent e) throws SQLException { //Para aplicar a la beca
        //Las listas no colaboran para la verificacion
       // if (!(nombretxt.getText().isEmpty() || emailtxt.getText().isEmpty() || f_nac.getValue() == null || t_doc.getValue() == null || emailtxt.getText().isEmpty() || direccion.getText().isEmpty() || estrato.getValue() == null || beca.getValue() == null)) {
            String n_beca = (String) beca.getValue();
            String n_asp = nombretxt.getText();
            String id_num = (num_doc.getText());
            String a_email = emailtxt.getText();
            LocalDate f_nacimiento = f_nac.getValue();
            int a_estrato = Integer.valueOf((String) estrato.getValue());
            String a_t_doc = (String) t_doc.getValue();
            String a_dir = direccion.getText();
            //List<String> doc_list = list_doc.getItems();
            /*st = conn.createStatement();
            rs = st.executeQuery("select count(id) from beca");
            int tempid = 0;
            while (rs.next()) {
                tempid = rs.getInt(1);
            }
            tempid++;*/
            System.out.println("Aspirante ID: " + id_num);
            st = conn.createStatement();
            //Query Crear Beca
            st.executeUpdate("insert into aspirante values('" + n_asp + "', '" + a_t_doc + "', '" + id_num + "', '" + a_email + "', '" + f_nacimiento + "', '" + a_dir + "', " + a_estrato + ")");
            beca.setValue(null);
            nombretxt.setText("");
            num_doc.setText("");
            emailtxt.setText("");
            f_nac.setValue(null);
            estrato.setValue(null);
           t_doc.setValue(null);
            direccion.setText("");
       /* } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos Incompletos");
            alert.setHeaderText("Debe de llenar todos los campos.");
            //alert.setContentText("No se pudo conectar con el servidor. ¿Está el servicio corriendo?");
            alert.show();
        }*/
    }

    @FXML
    void upload(ActionEvent e) { //Para subir archivos

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        req_info.setEditable(false);
        benef_info.setEditable(false);
        SQL = new ConexionMySQL();
        conn = SQL.config("test", "root", "manexrules23");
    }

}
