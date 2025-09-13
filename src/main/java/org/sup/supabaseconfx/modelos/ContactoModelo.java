package org.sup.supabaseconfx.modelos;

import java.math.BigDecimal;

public class ContactoModelo {
    private int id;
    private int idTipoContacto;
    private BigDecimal valorContacto;

    public ContactoModelo(int idTipoContacto, BigDecimal valorContacto) {
        this.idTipoContacto = idTipoContacto;
        this.valorContacto = valorContacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public BigDecimal getValorContacto() {
        return valorContacto;
    }

    public void setValorContacto(BigDecimal valorContacto) {
        this.valorContacto = valorContacto;
    }
}
