package org.sup.supabaseconfx.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.sup.supabaseconfx.agendaDao.UsuarioCrearDao;

public class CrearUsuarioControlador {

    UsuarioCrearDao crearContacto = new UsuarioCrearDao();
    Alert advertencias = new Alert(Alert.AlertType.WARNING);


    @FXML
    private TextField txtUsuario, txtContra, txtEmail;

    @FXML
    private PasswordField txtConfirmContra;

    @FXML
    public void crearNuevoUsuario() throws Exception {

        String usuario = txtUsuario.getText();
        String contrasenia = txtContra.getText();
        String confirmarContrasenia = txtConfirmContra.getText();
        String email = txtEmail.getText();


        if (usuario.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty() || email.isEmpty()) {
            advertencias.setTitle("ERROR DE VERIFICACION");
            advertencias.setContentText("LLENE TODOS LOS CAMPOS");
            advertencias.show();
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            advertencias.setTitle("ERROR DE COINCIDENCIA");
            advertencias.setContentText("LAS 2 CONTRASEÑAS DEBEN SER IGUALES");
            return;
        }
        crearContacto.insertarContacto(contrasenia, usuario, email);
        limpiarCredenciales();
    }

    public void limpiarCredenciales(){
        txtUsuario.clear();
        txtEmail.clear();
        txtContra.clear();
        txtConfirmContra.clear();
    }
}
