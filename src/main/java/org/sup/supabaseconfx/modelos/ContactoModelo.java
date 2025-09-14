package org.sup.supabaseconfx.modelos;

import java.math.BigDecimal;

public class ContactoModelo {
    private int idContacto;
    private String nombre;
    private String telefono;
    private String email;
    private int idTipoContacto;


    public ContactoModelo(int idContacto, String nombre, String telefono, String email, int idTipoContacto) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.idTipoContacto = idTipoContacto;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }
}
