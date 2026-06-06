package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.time.LocalDate;

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

    public static void salvarEmprestimos(List<Emprestimo> emprestimos) {
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

            List<String> linhas = Files.readAllLines(caminho);
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

    public static void carregarEmprestimos(Catalogo catalogo, List<Usuario> usuarios, List<Emprestimo> emprestimos) {
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
