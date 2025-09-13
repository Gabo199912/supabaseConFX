package org.sup.supabaseconfx.modelos;

public class TipoContactoModelo {
    private int id;
    private String tipo;

    public TipoContactoModelo(String tipo) {
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
