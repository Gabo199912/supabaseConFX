package org.sup.supabaseconfx.modelos;

public class UsuarioModelo {
    private int id;
    private String nombre;
    private String correo;
    private String contrasenia;
    private String salt;

    public UsuarioModelo(String nombre, String correo, String contrasenia, String salt) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.salt = salt;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
