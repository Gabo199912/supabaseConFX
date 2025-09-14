package org.sup.supabaseconfx.agendaDao;

import javafx.stage.Stage;

public interface UsuarioDao {
        void insertarContacto(String contrasenia, String usuario, String email) throws Exception;

        void ingresar(String usuario, String contrasenia, Stage LoginStage) throws Exception;
}
