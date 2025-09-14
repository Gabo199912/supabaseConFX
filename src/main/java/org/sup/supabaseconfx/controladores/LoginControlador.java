package org.sup.supabaseconfx.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sup.supabaseconfx.HelloApplication;
import org.sup.supabaseconfx.agendaDao.UsuarioCrearDao;
import org.sup.supabaseconfx.bd.BaseDatos;
import org.sup.supabaseconfx.bd.hash.configHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginControlador {
    UsuarioCrearDao verificarUsuario = new UsuarioCrearDao();
    private final Alert MENSAJES = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField txtUsuarioLogin;
    @FXML
    private PasswordField txtContrasenia;

    @FXML
    public void ingresar()throws Exception{
        String usuario = txtUsuarioLogin.getText();
        String contrasenia = txtContrasenia.getText();
        Stage stage = (Stage) txtUsuarioLogin.getScene().getWindow();


        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            MENSAJES.setTitle("ERROR");
            MENSAJES.setContentText("TODOS LOS CAMPOS DEBEN ESTAR LLENOS");
            MENSAJES.show();
            return;
        }

        verificarUsuario.ingresar(usuario, contrasenia, stage);
    }



    @FXML
    public void vistaCrearUsuario() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CrearUsuario.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load(), 336, 406);
            Stage stage = new Stage();
            stage.setTitle("Crear Usuario");
            stage.setX(200);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            throw new RuntimeException("error en la carga de la vista " + e);
        }
    }

}
