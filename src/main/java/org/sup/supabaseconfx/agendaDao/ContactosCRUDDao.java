package org.sup.supabaseconfx.agendaDao;

import javafx.scene.control.Alert;
import org.sup.supabaseconfx.bd.BaseDatos;
import org.sup.supabaseconfx.modelos.ContactoModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactosCRUDDao implements ContactosDao {
    private static Alert ADVERTENCIA = new Alert(Alert.AlertType.WARNING);
    private static Alert CONFIRMACION = new Alert(Alert.AlertType.CONFIRMATION);


    List<ContactoModelo> listaContactos = new ArrayList<>();


    @Override
    public void CrearContacto(String nombre, String telefono, String email, int idContacto) {
        String sql = "INSERT INTO contacto (nombre, telefono, email, id_tipo) VALUES (?,?,?,?);";

        try (Connection con = BaseDatos.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);
            ps.setInt(4, idContacto);
            ps.executeUpdate();

            CONFIRMACION.setTitle("CONTACTO CREADO");
            CONFIRMACION.setContentText("CONTACTO CREADO EXITOSAMENTE");
            CONFIRMACION.show();

        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            ADVERTENCIA.setTitle("ERROR");
            ADVERTENCIA.setContentText("ERROR AL CREAR EL CONTACTO");
            ADVERTENCIA.show();
        }
    }

    @Override
    public List<ContactoModelo> listarContactos() throws Exception {
        String sql = "SELECT id_contacto, nombre, telefono, email, id_tipo FROM contacto";
        listaContactos = new ArrayList<>();
        try (Connection con = BaseDatos.getConnection();
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery()){
            while (rs.next()){
               ContactoModelo contactoModelo = new ContactoModelo(
                       rs.getInt("id_contacto"),
                       rs.getString("nombre"),
                       rs.getString("telefono"),
                       rs.getString("email"),
                       rs.getInt("id_tipo")
               );
                listaContactos.add(contactoModelo);
            }


        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }


        return listaContactos;
    }

}
