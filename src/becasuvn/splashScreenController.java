/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becasuvn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author Jesus Lozano
 */
public class splashScreenController implements Initializable {

    @FXML
    private AnchorPane splash;
    Alert alert = new Alert(AlertType.ERROR);

    ConexionMySQL session = new ConexionMySQL();
    Throwable errorconn = null;

    boolean badadmin = false;

    public ConexionMySQL getConn() {
        return session;
    }

    public void adminLogin() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }

    public void launchLogin() {
        Stage ventanaApp = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        ventanaApp.setScene(scene);
        ventanaApp.setTitle("Login");
        ventanaApp.setResizable(false);
        ventanaApp.show();
    }

    public void showWarn() {

        /*System.out.println("nae nae nigga nae nae:  " + errorconn.getCause());
        StackTraceElement[] el = errorconn.getStackTrace();
        for (int i = 0; i < el.length; i++) {
            //System.out.println("1");
            System.out.println(el[i]);
            
        }*/
        String[] nae = errorconn.toString().split(":");
        String[] nigga = nae[1].split("\n");
        System.out.println(nigga[0]);
        switch (nigga[0]) {
            case " Communications link failure":
                alert.setTitle("Error de conexion");
                alert.setHeaderText("No se pudo conectar a la base de datos.");
                alert.setContentText("No se pudo conectar con el servidor. ¿Está el servicio corriendo?");
                break;
            case " Public Key Retrieval is not allowed":
                alert.setTitle("Error de conexion");
                alert.setHeaderText("No se pudo conectar a la base de datos.");
                alert.setContentText("Credenciales de administrador invalidas.");
                badadmin = true;
                break;

            default:
                alert.setTitle("Error de conexion");
                alert.setHeaderText("No se pudo conectar a la base de datos.");
                alert.setContentText("Error desconocido");
                break;
        }
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition transition = new FadeTransition(Duration.millis(2000), splash);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage ventana = (Stage) splash.getScene().getWindow();
                ventana.hide();
                Connection conn = session.config("test", "root", "manexrules23");
                errorconn = session.getConn_error();
                if (conn != null) {
                    System.out.println("Sesion lista");
                    launchLogin();
                } else {
                    System.out.println("Credenciales Invalidas");
                    showWarn();
                    if (badadmin){
                        //adminLogin();
                    }
                }

            }
        });
       

    }

}
