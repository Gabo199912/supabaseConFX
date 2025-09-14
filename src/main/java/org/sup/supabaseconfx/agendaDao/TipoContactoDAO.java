package org.sup.supabaseconfx.agendaDao;

import org.sup.supabaseconfx.modelos.TipoContactoModelo;

import java.util.List;

public interface TipoContactoDAO {
    void insertarTipoContacto(String descripcion);
    List<TipoContactoModelo> listarTipoContacto() throws Exception;
}
