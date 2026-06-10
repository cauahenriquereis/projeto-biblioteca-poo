package br.inatel.poo.biblioteca;

import br.inatel.poo.biblioteca.gui.TelaPrincipal;
import br.inatel.poo.biblioteca.model.Emprestimo;
import br.inatel.poo.biblioteca.persistence.GerenciadorArquivos;
import br.inatel.poo.biblioteca.service.Biblioteca;
import br.inatel.poo.biblioteca.service.Catalogo;

public class Main {
    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Biblioteca biblioteca = new Biblioteca(catalogo);
        GerenciadorArquivos.carregarCatalogo(catalogo);
        GerenciadorArquivos.carregarUsuarios(biblioteca);
        GerenciadorArquivos.carregarEmprestimos(catalogo, biblioteca.getUsuarios(), biblioteca.getEmprestimosAtivos());
        new TelaPrincipal(biblioteca);

    }
}