package br.inatel.poo.biblioteca.persistence;

import br.inatel.poo.biblioteca.model.*;
import br.inatel.poo.biblioteca.service.Biblioteca;
import br.inatel.poo.biblioteca.service.Catalogo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class GerenciadorArquivos {

    public static void salvarCatalogo(Catalogo catalogo) {
        StringBuilder conteudo = new StringBuilder();

        for (ItemBiblioteca item : catalogo.getItens()) {
            if (item instanceof Livro livro) {
                conteudo.append("LIVRO;")
                        .append(livro.getId()).append(";")
                        .append(livro.getTitulo()).append(";")
                        .append(livro.getAutor()).append(";")
                        .append(livro.getNumPaginas()).append("\n");
            } else if (item instanceof Revista revista) {
                conteudo.append("REVISTA;")
                        .append(revista.getId()).append(";")
                        .append(revista.getTitulo()).append(";")
                        .append(revista.getEdicao()).append(";")
                        .append(revista.getMesAno()).append("\n");
            } else if (item instanceof DVD dvd) {
                conteudo.append("DVD;")
                        .append(dvd.getId()).append(";")
                        .append(dvd.getTitulo()).append(";")
                        .append(dvd.getDiretor()).append(";")
                        .append(dvd.getDuracao()).append("\n");
            }
        }

        try {
            Files.writeString(Path.of("data/catalogo.txt"), conteudo.toString());
            System.out.println("Catálogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar catálogo: " + e.getMessage());
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        StringBuilder conteudo = new StringBuilder();

        for (Usuario usuario : usuarios) {
            conteudo.append(usuario.getId()).append(";")
                    .append(usuario.getNome()).append(";")
                    .append(usuario.getCpf()).append(";")
                    .append(usuario.getTelefone()).append("\n");
        }

        try {
            Files.writeString(Path.of("data/usuario.txt"), conteudo.toString());
            System.out.println("Usuários salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public static void salvarEmprestimos(java.util.List<Emprestimo> emprestimos) {
        StringBuilder conteudo = new StringBuilder();

        for (Emprestimo emprestimo : emprestimos) {
            conteudo.append(emprestimo.getUsuario().getId()).append(";")
                    .append(emprestimo.getItem().getId()).append(";")
                    .append(emprestimo.getDataSaida()).append(";")
                    .append(emprestimo.getDataPrevista()).append(";")
                    .append(emprestimo.getDataRetorno()).append(";")
                    .append(emprestimo.getStatus()).append("\n");
        }

        try {
            Files.writeString(Path.of("data/emprestimos.txt"), conteudo.toString());
            System.out.println("Empréstimos salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimos: " + e.getMessage());
        }
    }

    public static void carregarCatalogo(Catalogo catalogo) {
        try {
            Path caminho = Path.of("data/catalogo.txt");
            if (!Files.exists(caminho)) return;

            java.util.List<String> linhas = Files.readAllLines(caminho);
            for (String linha : linhas) {
                String[] dados = linha.split(";");
                switch (dados[0]) {
                    case "LIVRO" -> catalogo.adicionarItem(
                            new Livro(Integer.parseInt(dados[1]), dados[2], dados[3], Integer.parseInt(dados[4]))
                    );
                    case "REVISTA" -> catalogo.adicionarItem(
                            new Revista(Integer.parseInt(dados[1]), dados[2], Integer.parseInt(dados[3]), dados[4])
                    );
                    case "DVD" -> catalogo.adicionarItem(
                            new DVD(Integer.parseInt(dados[1]), dados[2], dados[3], Integer.parseInt(dados[4]))
                    );
                }
            }
            System.out.println("Catálogo carregado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar catálogo: " + e.getMessage());
        }
    }

    public static void carregarUsuarios(Biblioteca biblioteca) {
        try {
            Path caminho = Path.of("data/usuario.txt");
            if (!Files.exists(caminho)) return;

            List<String> linhas = Files.readAllLines(caminho);

            for (String linha : linhas) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String cpf = dados[2];
                String telefone = dados[3];

                Usuario usuario = new Usuario(id, nome, cpf, telefone);
                biblioteca.cadastrarUsuario(usuario);
            }

            System.out.println("Usuários carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public static void carregarEmprestimos(Catalogo catalogo, java.util.List<Usuario> usuarios, java.util.List<Emprestimo> emprestimos) {
        try {
            Path caminho = Path.of("data/emprestimos.txt");
            if (!Files.exists(caminho)) return;

            List<String> linhas = Files.readAllLines(caminho);
            for (String linha : linhas) {
                String[] dados = linha.split(";");

                int idUsuario = Integer.parseInt(dados[0]);
                int idItem = Integer.parseInt(dados[1]);
                LocalDate dataSaida = LocalDate.parse(dados[2]);
                LocalDate dataPrevista = LocalDate.parse(dados[3]);

                Usuario usuario = usuarios.stream()
                        .filter(u -> u.getId() == idUsuario)
                        .findFirst().orElse(null);

                ItemBiblioteca item = catalogo.getItens().stream()
                        .filter(i -> i.getId() == idItem)
                        .findFirst().orElse(null);

                if (usuario != null && item != null) {
                    Emprestimo emprestimo = new Emprestimo(usuario, item, dataSaida, dataPrevista);
                    emprestimos.add(emprestimo);
                }
            }
            System.out.println("Empréstimos carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar empréstimos: " + e.getMessage());
        }
    }


}
