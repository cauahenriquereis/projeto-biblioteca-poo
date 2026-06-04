package org.example;

public abstract class ItemBiblioteca {

    private int id;
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(int id, String titulo){
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true; // por padrão, os itens ja inicializam como disponíveis
    }

    public abstract void exibirDetalhes();

    public int getId() { return id; }

    public String getTitulo() { return titulo; }

    public boolean isDisponivel() { return disponivel;}

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
