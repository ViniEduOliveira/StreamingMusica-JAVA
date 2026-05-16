package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

// NOVO: Classe abstrata para herança
public abstract class ItemReproducao implements Reproduzivel {
    protected String titulo;

    public ItemReproducao(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() { return titulo; }
    
    public void setTitulo(String titulo) { this.titulo = titulo; }
}