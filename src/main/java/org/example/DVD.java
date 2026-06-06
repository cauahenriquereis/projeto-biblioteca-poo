package org.example;

public class DVD extends ItemBiblioteca{

    private String diretor;
    private int duracao;

    public DVD(int id, String titulo, String diretor, int duracao) {
        super(id, titulo);
        this.diretor = diretor;
        this.duracao = duracao;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("DVD: " + getTitulo() + " | Diretor: " + this.diretor + " | Duração em minutos: " + duracao);
    }

    public String getDiretor() {
        return diretor;
    }

    public int getDuracao() {
        return duracao;
    }
}
