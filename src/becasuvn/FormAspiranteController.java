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
    JFXComboBox<String> t_doc, estrato, beca;

    @FXML
    JFXDatePicker f_nac;

    @FXML
    JFXListView req_info, benef_info;

    void comboBoxReady() {
        t_doc.getItems().add("Tarjeta de Identidad");
        t_doc.getItems().add("Cedula Extranjera");
        t_doc.getItems().add("Cedúla de Ciudadanía");
        estrato.getItems().add("1");
        estrato.getItems().add("2");
        estrato.getItems().add("3");
        estrato.getItems().add("4");
        estrato.getItems().add("5");
        estrato.getItems().add("6");
    }

    @FXML
    void listChangerCB(ActionEvent e) throws SQLException {
        req_info.getItems().clear();
        benef_info.getItems().clear();
        String beca_n = beca.getValue();
        //select r.requisito from requisito as r, beca as b where b.id = r.beca_id and b.nombre = "qweqeqeqw"
        st = conn.createStatement();
        rs = st.executeQuery("select r.requisito from requisito as r, beca as b where b.id = r.beca_id and b.nombre = '" + beca_n + "'");
        String temp = "";
        while (rs.next()) {
            //split por comas
            temp = rs.getString(1);
            String tmp[] = temp.split(",");
            for (String s : tmp) {
                req_info.getItems().add(s);
            }
        }
        st = conn.createStatement();
        rs = st.executeQuery("select ben.beneficios from beneficio as ben, beca as b where b.id = ben.beca_id and b.nombre = '" + beca_n + "'");
        String tempb = "";
        while (rs.next()) {
            //split por comas
            tempb = rs.getString(1);
            String tmp[] = tempb.split(",");
            for (String s : tmp) {
                benef_info.getItems().add(s);

            }
        }
    }

    @FXML
    void aplicar(ActionEvent e) throws SQLException { //Para aplicar a la beca
        //Las listas no colaboran para la verificacion
        if (!(nombretxt.getText().isEmpty() || emailtxt.getText().isEmpty() || f_nac.getValue() == null || t_doc.getValue() == null || emailtxt.getText().isEmpty() || direccion.getText().isEmpty() || estrato.getValue() == null || beca.getValue() == null)) {
            String n_beca = (String) beca.getValue();
            String n_asp = nombretxt.getText();
            String id_num = (num_doc.getText());
            String a_email = emailtxt.getText();
            LocalDate f_nacimiento = f_nac.getValue();
            int a_estrato = Integer.valueOf((String) estrato.getValue());
            String a_t_doc = (String) t_doc.getValue();
            String a_dir = direccion.getText();
            //List<String> doc_list = list_doc.getItems();
            st = conn.createStatement();
            rs = st.executeQuery("select id from beca where nombre = '"+n_beca+"'");
            int becaid = 0;
            while (rs.next()) {
                becaid = rs.getInt(1);
            }
            System.out.println("Aspirante ID: " + id_num);
            //Verificar si ya existe el aspirante
            st = conn.createStatement();
            //Query Aspirante
            st.executeUpdate("insert into aspirante values('" + n_asp + "', '" + a_t_doc + "', '" + id_num + "', '" + a_email + "', '" + f_nacimiento + "', '" + a_dir + "', " + a_estrato + ")");
            beca.setValue(null);
            nombretxt.setText("");
            num_doc.setText("");
            emailtxt.setText("");
            f_nac.setValue(null);
            estrato.setValue(null);
            t_doc.setValue(null);
            direccion.setText("");
            
            //Query para crear formulario
            st = conn.createStatement();
            rs = st.executeQuery("select count(id) from formulario where aspirante_doc = '"+id_num+"'");
            int n_form = 0;
            while (rs.next()) {
                n_form = rs.getInt(1);
            }
            n_form++;
            String f_idtmp = id_num.substring(0, 6)+becaid ;
            int f_id = Integer.valueOf(f_idtmp);
            System.out.println("Formulario ID: "+ f_id);
            st.executeUpdate("insert into formulario values('" + f_id + "', '" + becaid + "', '" + id_num + "')");
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos Incompletos");
            alert.setHeaderText("Debe de llenar todos los campos.");
            //alert.setContentText("No se pudo conectar con el servidor. ¿Está el servicio corriendo?");
            alert.show();
        }
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
        comboBoxReady();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select nombre from beca");
            String becas = "";
            while (rs.next()) {
                becas = rs.getString(1);
                beca.getItems().add(becas);
            }
        } catch (Exception e) {

        }
    }

}
