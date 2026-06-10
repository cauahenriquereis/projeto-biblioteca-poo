package br.inatel.poo.biblioteca.model;

public class Livro extends ItemBiblioteca {

    private String autor;
    private int numPaginas;

    public Livro(int id, String titulo, String autor, int numPaginas) {
        super(id, titulo);
        this.autor = autor;
        this.numPaginas = numPaginas;

    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Livro: " + getTitulo() + " | Autor: " + this.autor + " | Páginas: " + this.numPaginas);
    }

    public String getAutor() {
        return autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }
}



