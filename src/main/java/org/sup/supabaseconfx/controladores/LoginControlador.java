package org.sup.supabaseconfx.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sup.supabaseconfx.HelloApplication;
import org.sup.supabaseconfx.agendaDao.ContactoDao;

import java.io.IOException;

public class LoginControlador {


    @FXML
    public void pruebaConexion(){
        ContactoDao contactoDao = new ContactoDao();

        contactoDao.insertarContacto(new org.sup.supabaseconfx.modelos.TipoContactoModelo("prueba"));
    }

    @FXML
    public void vistaCrearUsuario() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CrearUsuario.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load(), 327, 385);
            Stage stage = new Stage();
            stage.setTitle("Crear Usuario");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            throw new RuntimeException("error en la carga de la vista " + e);
        }
    }

}
