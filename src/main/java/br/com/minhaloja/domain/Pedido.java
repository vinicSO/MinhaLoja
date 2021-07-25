package br.com.minhaloja.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date instante;

    public Pedido() {
    }

    public Pedido(Integer id, Date instante) {
        this.id = id;
        this.instante = instante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
