package becasuvn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jesus Lozano
 */
public class ConexionMySQL {

    // se carga el driver
    private String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    private String database = "";

    // Host
    private String hostname = "localhost";

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://localhost" + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    private String username = "";

    // Clave de usuario
    private String password = "";

    private Throwable conn_error = null;

    public Throwable getConn_error() {
        return conn_error;
    }

    public Connection config(String dbname, String username, String password) {
        this.database = dbname;
        this.username = username;
        this.password = password;
        System.out.println("D: " + this.database + " U: " + this.username + " P: " + this.password);
        url = "jdbc:mysql://localhost" + ":" + port + "/" + database + "?useSSL=false";
        Connection conn = conectarMySQL();
        return conn;
    }

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión lista");
        } catch (ClassNotFoundException | SQLException e) {
            //System.out.println(e.toString());
            setConn_error(e);
            
        }
        return conn;
    }

    public void setConn_error(Throwable conn_error) {
        this.conn_error = conn_error;
    }

}
