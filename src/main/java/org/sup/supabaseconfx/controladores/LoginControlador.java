package org.sup.supabaseconfx.controladores;

import javafx.fxml.FXML;
import org.sup.supabaseconfx.agendaDao.ContactoDao;

public class ConeccionControlador {


    @FXML
    public void pruebaConexion(){
        ContactoDao contactoDao = new ContactoDao();

        contactoDao.insertarContacto(new org.sup.supabaseconfx.modelos.TipoContactoModelo("prueba"));
    }

}
