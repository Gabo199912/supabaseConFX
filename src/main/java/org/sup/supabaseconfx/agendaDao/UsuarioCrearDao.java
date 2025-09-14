package org.sup.supabaseconfx.agendaDao;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.sup.supabaseconfx.HelloApplication;
import org.sup.supabaseconfx.bd.BaseDatos;
import org.sup.supabaseconfx.bd.hash.configHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioCrearDao implements UsuarioDao {
    private final Alert ADVERTENCIA = new Alert(Alert.AlertType.WARNING);
    private final Alert MENSAJE = new Alert(Alert.AlertType.CONFIRMATION);

    @Override
    public void insertarContacto(String contrasenia, String usuario, String email)throws Exception {
        String sql = "INSERT INTO usuario (nombre, email, contrasenia, salt) VALUES (?,?,?,?);";

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            byte[] salt = configHash.generarSalt();
            byte[] contraseñaHash = configHash.generarHash(contrasenia.toCharArray(), salt);

            ps.setString(1, usuario);
            ps.setString(2, email);
            ps.setString(3, configHash.toBase64(contraseñaHash));
            ps.setString(4, configHash.toBase64(salt));

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                MENSAJE.setTitle("CONFIRMACION");
                MENSAJE.setContentText("USUARIO CREADO EXITOSAMENTE");
                MENSAJE.show();
            }
        } catch (SQLException e) {
            String mensajeError = e.getMessage();

            if (mensajeError.contains("EL USUARIO YA EXISTE")){
                ADVERTENCIA.setTitle("ERROR");
                ADVERTENCIA.setContentText("EL USUARIO YA EXISTE, ESTE CAMPO DEBE SER UNICO");
                ADVERTENCIA.show();
            }else {
                System.out.println(e.getErrorCode());
            }
        }
    }

    @Override
    public void ingresar(String usuario, String contrasenia, Stage loginStage) throws Exception {
        String sql = "SELECT nombre, contrasenia,salt FROM usuario WHERE nombre = ?";

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String contraseniaRecuperada = rs.getString("contrasenia");
                String saltRecuperado = rs.getString("salt");
                byte[] saltByteRecuperada = configHash.fromBase64(saltRecuperado);
                byte[] contraseniaByteRecuperada = configHash.fromBase64(contraseniaRecuperada);

                boolean ok = configHash.comprobarUsuario(contrasenia.toCharArray(), saltByteRecuperada, contraseniaByteRecuperada);

                if (ok){
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Contactos.fxml"));

                    try {
                        loginStage.close();

                        Scene scene = new Scene(fxmlLoader.load(), 414, 402);
                        Stage stage = new Stage();
                        stage.setTitle("BIENVENIDO");
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();

                    }catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }else {
                    System.out.println("no se reconocio el usuario");
                }


            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
