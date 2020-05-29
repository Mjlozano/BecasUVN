/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */

/*I would go to you if I could fly
   so i'll grip my seat and close my eyes
   There are things that feel to big to send along in letters to the clouds
    I don't need to want what I don't have
    Getting trapped in stories at my hands
    Promising me a future that was never mine and was taken from someone else

 */
public class BecaFormController implements Initializable {

    ConexionMySQL SQL;
    Connection conn;
    Statement st = null;
    ResultSet rs = null;

    @FXML
    JFXTextField nombreBecatxt, n_cupostxt;
    @FXML
    JFXDatePicker f_iniciotxt, f_finaltxt;
    @FXML
    JFXTextField beneficiostxt, requisitostxt, doctxt;
    @FXML
    JFXListView<String> list_benef, list_req, list_doc;

    @FXML
    void crearBeca(ActionEvent e) throws SQLException {    //Metodo para insertar una beca
        //Las listas no colaboran para la verificacion
        if (!(nombreBecatxt.getText().isEmpty() || n_cupostxt.getText().isEmpty() || f_iniciotxt.getValue() == null || f_finaltxt.getValue() == null )){//|| list_benef.getItems() == null || list_req.getItems() == null || list_doc.getItems() == null)) {
            String nBeca = nombreBecatxt.getText();
            String n_cupos = n_cupostxt.getText();
            LocalDate f_inicio = f_iniciotxt.getValue();
            LocalDate f_final = f_finaltxt.getValue();
            //Date  f_inicio = Date.from(f_iniciotmp.atStartOfDay(ZoneId.systemDefault()).toInstant());
            //Date  f_final = Date.from(f_finaltmp.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<String> ben_list = list_benef.getItems();
            List<String> req_list = list_req.getItems();
            List<String> doc_list = list_doc.getItems();

            String dldb = "";
            String rldb = "";
            String bldb = "";
            for (String dc : doc_list) {
                dldb = dc + "," + dldb;
            }

            for (String r : req_list) {
                rldb = r + "," + rldb;
            }

            for (String b : ben_list) {
                bldb = b + "," + bldb;
            }
            
             //Query Crear Beca
            st = conn.createStatement();
            rs = st.executeQuery("select count(id) from beca");
            int tempid = 0;
            while (rs.next()) {
                tempid = rs.getInt(1);
            }
            tempid++;
            System.out.println("Beca ID: " + tempid);
            st = conn.createStatement();
            st.executeUpdate("insert into beca values(" + tempid + ", '" + nBeca + "', '2020-01', 1, '" + f_inicio + "', '" + f_final + "', " + n_cupos + ")");
            
            //Query para documento
            st = conn.createStatement();
            rs = st.executeQuery("select count(id) from documentob");
            int tempidd = 0;
            while (rs.next()) {
                tempidd = rs.getInt(1);
            }
            tempidd++;
            st.executeUpdate("insert into documentob values(" + tempidd + ", '"+dldb+"', "+tempid+
            ")");
            
            //Query para beneficio
             st = conn.createStatement();
            rs = st.executeQuery("select count(id) from beneficio");
            int tempidb = 0;
            while (rs.next()) {
                tempidb = rs.getInt(1);
            }
            tempidb++;
            st.executeUpdate("insert into beneficio values(" + tempidb + ", '"+bldb+"', "+tempid+
            ")");
            
            
            //Query para beneficio
             st = conn.createStatement();
            rs = st.executeQuery("select count(id) from requisito");
            int tempidr = 0;
            while (rs.next()) {
                tempidr = rs.getInt(1);
            }
            tempidr++;
            st.executeUpdate("insert into requisito values(" + tempidr + ", '"+rldb+"', "+tempid+
            ")");
            
            nombreBecatxt.setText("");
            n_cupostxt.setText("");
            f_iniciotxt.setValue(null);
            f_finaltxt.setValue(null);
            list_benef.getItems().clear();
            list_req.getItems().clear();
            list_doc.getItems().clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos Incompletos");
            alert.setHeaderText("Debe de llenar todos los campos.");
            //alert.setContentText("No se pudo conectar con el servidor. ¿Está el servicio corriendo?");
            alert.show();
        }
    }

    @FXML
    void addDoc(ActionEvent e) {  //Metodo para añadir un documento
        String tmp = doctxt.getText();
        list_doc.getItems().add(tmp);
        doctxt.setText("");
    }

    @FXML
    void addReq(ActionEvent e) {  //Metodo para añadir un requisito
        String tmp = requisitostxt.getText();
        list_req.getItems().add(tmp);
        requisitostxt.setText("");
    }

    @FXML
    void addBenef(ActionEvent e) {  //Metodo para añadir un beneficio
        String tmp = beneficiostxt.getText();
        list_benef.getItems().add(tmp);
        beneficiostxt.setText("");
    }

    @FXML
    void atras(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Becas UVN");
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SQL = new ConexionMySQL();
        conn = SQL.config("test", "root", "manexrules23");
    }

}
