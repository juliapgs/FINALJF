package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Tenis extends DefaultEntity {

    @Column(length = 50, nullable = false)
    private String marca;

    @Column(length = 50, nullable = false)
    private String modelo;

    private String nomeImagem;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CategoriaTenis categoria;

    @Column(columnDefinition = "INT CHECK (estoque >= 0)")
    // @Check(constraints = "estoque >= 0")
    private Integer estoque;

    @Column(length = 30)
    private String cor;

    @Column(length = 2)
    private Integer tamanho;

    @Column(nullable = false)
    private Double valor;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    
    public CategoriaTenis getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTenis categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}

 
