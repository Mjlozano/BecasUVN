/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class DebugController implements Initializable {
    
    ConexionMySQL session;
    Connection conn;
    Statement st = null;
    ResultSet rs = null;

    @FXML
    JFXTextArea queryarea;
    
    @FXML
    public void testConnection(){
        session = new ConexionMySQL();
        conn = session.config("test", "root", "manexrules23");
    }
    
    @FXML
    public void testSQL() throws SQLException{
        st = conn.createStatement();
        rs = st.executeQuery(queryarea.getText());
        while (rs.next()) {
                //Retrieve by column name
                Date fecha =  rs.getDate("fecha");
                System.out.println(fecha.toString());
            }
            rs.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
}
