package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Biblioteca biblioteca = new Biblioteca(catalogo);

        Livro livro1 = new Livro(1, "Clean Code", "Robert Martin", 431);
        Livro livro2 = new Livro(2, "O Hobbit", "J.R.R Tolkien", 336);
        Revista revista1 = new Revista(3, "National Geographic", 200, "Janeiro 2025");
        DVD dvd1 = new DVD(4, "Inception", "Christopher Nolan", 148);

        catalogo.adicionarItem(livro1);
        catalogo.adicionarItem(livro2);
        catalogo.adicionarItem(revista1);
        catalogo.adicionarItem(dvd1);

        Usuario usuario1 = new Usuario(1, "Cauã", "123.456.789-00", "(35) 99999-9999");

        biblioteca.cadastrarUsuario(usuario1);

        System.out.println("*** CATÁLOGO COMPLETO ***");
        catalogo.listarTodos();

        System.out.println("\n*** REALIZANDO EMPRÉSTIMO ***");
        try {
            biblioteca.realizarEmprestimo(usuario1, livro1, LocalDate.now().plusDays(7));
            System.out.println("Empréstimo realizado com sucesso!");
        } catch (ItemIndisponivelException | LimiteEmprestimoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n*** ITENS DISPONÍVEIS ***");
        catalogo.listarDisponiveis().forEach(item -> item.exibirDetalhes());


        GerenciadorArquivos.salvarCatalogo(catalogo);
        GerenciadorArquivos.salvarEmprestimos(biblioteca.getEmprestimosAtivos());
    }

}