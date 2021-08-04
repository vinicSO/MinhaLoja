package br.com.minhaloja.dto;

import br.com.minhaloja.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Integer quantidadeProdutosRelacionados;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
        quantidadeProdutosRelacionados = obj.getProdutos().size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeProdutosRelacionados() {
        return quantidadeProdutosRelacionados;
    }
}