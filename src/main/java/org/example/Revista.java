package org.example;

public class Revista extends ItemBiblioteca{

    private int edicao;
    private String mesAno;

    public Revista(int id, String titulo, int edicao, String mesAno) {
        super(id, titulo);
        this.edicao = edicao;
        this.mesAno = mesAno;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Revista: " + getTitulo() + " | Edição: " + this.edicao + " | Mês e Ano de Lançamento: " + this.mesAno);
    }

    public int getEdicao() {
        return edicao;
    }

    public String getMesAno() {
        return mesAno;
    }
}


