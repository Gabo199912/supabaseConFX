package org.sup.supabaseconfx.agendaDao;

import org.sup.supabaseconfx.bd.BaseDatos;
import org.sup.supabaseconfx.modelos.TipoContactoModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ContactoDao implements contactosDao{

    @Override
    public TipoContactoModelo insertarContacto(TipoContactoModelo Tipocontacto) {
        String sql = "INSERT INTO tipoContacto (tipo) VALUES (?)";

        try (Connection con = BaseDatos.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);){

            ps.setString(1,Tipocontacto.getTipo());
            ps.executeUpdate();

            System.out.println("Registro insertado");
        }catch (Exception e){
            throw new RuntimeException("error en la conexion " + e);
        }


        return null;
    }
}
