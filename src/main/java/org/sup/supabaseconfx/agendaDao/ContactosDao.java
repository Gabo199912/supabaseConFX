package org.sup.supabaseconfx.agendaDao;

import org.sup.supabaseconfx.modelos.ContactoModelo;

import java.util.List;

public interface ContactosDao {
    void CrearContacto(String nombre, String telefono, String email, int idContacto);
    List<ContactoModelo> listarContactos() throws Exception;

}
