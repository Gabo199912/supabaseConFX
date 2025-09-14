package org.sup.supabaseconfx.agendaDao;

import javafx.scene.control.Alert;

public class ContactosCRUDDao implements ContactosDao {
    private static Alert ADVERTENCIA = new Alert(Alert.AlertType.WARNING);
    private static Alert CONFIRMACION = new Alert(Alert.AlertType.CONFIRMATION);

    @Override
    public void CrearContacto(String nombre, String telefono, String email, String tipoContacto) {

    }
}
