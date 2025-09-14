package org.sup.supabaseconfx.agendaDao;

import org.sup.supabaseconfx.bd.BaseDatos;
import org.sup.supabaseconfx.modelos.TipoContactoModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoContactoCRUD implements TipoContactoDAO{

    @Override
    public void insertarTipoContacto(String descripcion) {
        String sql = "INSERT INTO tipoContacto (descripcion) VALUES (?);";

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, descripcion);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<TipoContactoModelo> listarTipoContacto() throws Exception {
        String sql = "SELECT id_tipo, descripcion FROM tipoContacto";
        List<TipoContactoModelo> listaContactos = new ArrayList<>();

        try (Connection con = BaseDatos.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                TipoContactoModelo tipoContactoModelo = new TipoContactoModelo(
                        rs.getInt("id_tipo"),
                        rs.getString("descripcion")
                );

                listaContactos.add(tipoContactoModelo);
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return listaContactos;
    }
}
