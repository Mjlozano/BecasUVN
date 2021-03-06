/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCombination;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jesus Lozano
 */
public class DashboardController implements Initializable {

    @FXML
    Menu debugmenu;

    @FXML
    Label n_aspiranteslabel, n_becaslabel;

    ConexionMySQL SQL;
    Connection conn;
    Statement st = null;
    ResultSet rs = null;

    @FXML
    private JFXTreeTableView<Aspirante> treeView;

    @FXML
    private JFXTreeTableView<Beca> becaView;

    ObservableList<Aspirante> aspirantes = FXCollections.observableArrayList();
    ObservableList<Beca> becas = FXCollections.observableArrayList();

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
    private void filter(ActionEvent event) {
    }

    class Beca extends RecursiveTreeObject<Beca> { //Clase para mostrar los datos

        StringProperty beca;
        StringProperty convabierta;
        StringProperty n_asp;

        public Beca(String beca, String convabierta, String n_asp) {
            this.beca = new SimpleStringProperty(beca);
            this.convabierta = new SimpleStringProperty(convabierta);
            this.n_asp = new SimpleStringProperty(n_asp);
        }

    }

    class Aspirante extends RecursiveTreeObject<Aspirante> { //Clase para mostrar los datos

        StringProperty beca;
        StringProperty nombre;
        StringProperty ide;

        public Aspirante(String ide, String nombre, String beca) {
            this.ide = new SimpleStringProperty(ide); //#1        
            this.nombre = new SimpleStringProperty(nombre); //#2
            this.beca = new SimpleStringProperty(beca); //#3
        }

    }

    void showTable() { // codigo bien 70triplehp solo para mostrar unos datos en una tabla
        JFXTreeTableColumn<Aspirante, String> id = new JFXTreeTableColumn<>("Identificación");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Aspirante, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Aspirante, String> param) {
                return param.getValue().getValue().ide;
            }
        });

        JFXTreeTableColumn<Aspirante, String> name = new JFXTreeTableColumn<>("Nombre");
        name.setPrefWidth(200);
        id.setResizable(true);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Aspirante, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Aspirante, String> param) {
                return param.getValue().getValue().nombre;
            }
        });

        JFXTreeTableColumn<Aspirante, String> scholarS = new JFXTreeTableColumn<>("Beca");
        scholarS.setPrefWidth(150);
        scholarS.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Aspirante, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Aspirante, String> param) {
                return param.getValue().getValue().beca;
            }
        });

        //addP(); //agrega los elementos a mostrar
        final TreeItem<Aspirante> root = new RecursiveTreeItem<Aspirante>(aspirantes, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(id, name, scholarS);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    void showTableBeca() { // codigo bien 70triplehp solo para mostrar unos datos en una tabla
        JFXTreeTableColumn<Beca, String> id = new JFXTreeTableColumn<>("Nombre");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Beca, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Beca, String> param) {
                return param.getValue().getValue().beca;
            }
        });

        JFXTreeTableColumn<Beca, String> name = new JFXTreeTableColumn<>("Conv. Activa");
        name.setPrefWidth(200);
        id.setResizable(true);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Beca, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Beca, String> param) {
                return param.getValue().getValue().convabierta;
            }
        });

        JFXTreeTableColumn<Beca, String> scholarS = new JFXTreeTableColumn<>("N. de aspirantes");
        scholarS.setPrefWidth(150);
        scholarS.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Beca, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Beca, String> param) {
                return param.getValue().getValue().n_asp;
            }
        });

        //addP(); //agrega los elementos a mostrar
        final TreeItem<Beca> root = new RecursiveTreeItem<Beca>(becas, RecursiveTreeObject::getChildren);
        becaView.getColumns().setAll(id, name, scholarS);
        becaView.setRoot(root);
        becaView.setShowRoot(false);
    }

    void addP() { //Aqui añade los objetos que va a mostrar la tabla
        aspirantes.add(new Aspirante("9595125", "Jesus Lozano", "generacion E"));
        aspirantes.add(new Aspirante("313213", "Juancho", "Electricaribe"));
    }

    @FXML
    private void NuevaBeca(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BecaForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Becas UVN");
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTable();
        showTableBeca();

        SQL = new ConexionMySQL();
        conn = SQL.config("uvn", "root", "freischalten");
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select count(id) from beca");
            int tempid = 0;
            while (rs.next()) {
                tempid = rs.getInt(1);
            }
            n_becaslabel.setText(String.valueOf(tempid));
            st = conn.createStatement();
            rs = st.executeQuery("select count(doc) from aspirante");
            int nasp = 0;
            while (rs.next()) {
                nasp = rs.getInt(1);
            }
            n_aspiranteslabel.setText(String.valueOf(nasp));
            st = conn.createStatement();
            rs = st.executeQuery("select * from aspirante");
            while (rs.next()) {
                Aspirante e = new Aspirante(rs.getString("doc"), rs.getString("nombre"), "ahora ponemos lo de beca xd");
                aspirantes.add(e);
            }
            st = conn.createStatement();
            rs = st.executeQuery("select * from beca");
            while (rs.next()) {
                String convstatus = "";
                if (rs.getString("abierto").equals("1")) {
                    convstatus = "Sí";
                } else {
                    convstatus = "No";
                }
                Beca e = new Beca(rs.getString("nombre"), convstatus, String.valueOf(rs.getInt("cupos")));
                becas.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
