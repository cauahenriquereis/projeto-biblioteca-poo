package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.model.DVD;
import br.inatel.poo.biblioteca.model.Livro;
import br.inatel.poo.biblioteca.model.Revista;
import br.inatel.poo.biblioteca.service.Biblioteca;
import br.inatel.poo.biblioteca.persistence.GerenciadorArquivos;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroItem extends JFrame {

    private Biblioteca biblioteca;

    public TelaCadastroItem(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Cadastrar Item");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(9, 2, 10, 10));

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Livro", "Revista", "DVD"});

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();

        // campos do Livro
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();

        JLabel lblNumPaginas = new JLabel("Número de Páginas:");
        JTextField txtNumPaginas = new JTextField();

        // campos da Revista
        JLabel lblEdicao = new JLabel("Edição:");
        JTextField txtEdicao = new JTextField();

        JLabel lblMesAno = new JLabel("Mês e Ano:");
        JTextField txtMesAno = new JTextField();

        // campos do DVD
        JLabel lblDiretor = new JLabel("Diretor:");
        JTextField txtDiretor = new JTextField();

        JLabel lblDuracao = new JLabel("Duração (min):");
        JTextField txtDuracao = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(lblTipo);
        painel.add(comboTipo);
        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblAutor);
        painel.add(txtAutor);
        painel.add(lblNumPaginas);
        painel.add(txtNumPaginas);
        painel.add(lblEdicao);
        painel.add(txtEdicao);
        painel.add(lblMesAno);
        painel.add(txtMesAno);
        painel.add(lblDiretor);
        painel.add(txtDiretor);
        painel.add(lblDuracao);
        painel.add(txtDuracao);
        painel.add(btnCadastrar);
        painel.add(btnVoltar);

        add(painel);

        lblEdicao.setVisible(false);
        txtEdicao.setVisible(false);
        lblMesAno.setVisible(false);
        txtMesAno.setVisible(false);
        lblDiretor.setVisible(false);
        txtDiretor.setVisible(false);
        lblDuracao.setVisible(false);
        txtDuracao.setVisible(false);

        comboTipo.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();

            lblAutor.setVisible(false);
            txtAutor.setVisible(false);
            lblNumPaginas.setVisible(false);
            txtNumPaginas.setVisible(false);
            lblEdicao.setVisible(false);
            txtEdicao.setVisible(false);
            lblMesAno.setVisible(false);
            txtMesAno.setVisible(false);
            lblDiretor.setVisible(false);
            txtDiretor.setVisible(false);
            lblDuracao.setVisible(false);
            txtDuracao.setVisible(false);

            switch (tipo) {
                case "Livro" -> {
                    lblAutor.setVisible(true);
                    txtAutor.setVisible(true);
                    lblNumPaginas.setVisible(true);
                    txtNumPaginas.setVisible(true);
                }
                case "Revista" -> {
                    lblEdicao.setVisible(true);
                    txtEdicao.setVisible(true);
                    lblMesAno.setVisible(true);
                    txtMesAno.setVisible(true);
                }
                case "DVD" -> {
                    lblDiretor.setVisible(true);
                    txtDiretor.setVisible(true);
                    lblDuracao.setVisible(true);
                    txtDuracao.setVisible(true);
                }
            }
        });

        btnCadastrar.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            String titulo = txtTitulo.getText();
            int id = biblioteca.gerarIdItem();

            switch (tipo) {
                case "Livro" -> {
                    String autor = txtAutor.getText();
                    int numPaginas = Integer.parseInt(txtNumPaginas.getText());
                    biblioteca.getCatalogo().adicionarItem(new Livro(id, titulo, autor, numPaginas));
                }
                case "Revista" -> {
                    int edicao = Integer.parseInt(txtEdicao.getText());
                    String mesAno = txtMesAno.getText();
                    biblioteca.getCatalogo().adicionarItem(new Revista(id, titulo, edicao, mesAno));
                }
                case "DVD" -> {
                    String diretor = txtDiretor.getText();
                    int duracao = Integer.parseInt(txtDuracao.getText());
                    biblioteca.getCatalogo().adicionarItem(new DVD(id, titulo, diretor, duracao));
                }
            }

            JOptionPane.showMessageDialog(this, "Item cadastrado com sucesso!");

           GerenciadorArquivos.salvarCatalogo(biblioteca.getCatalogo());

            // limpa campos comuns
            txtTitulo.setText("");

            // limpa campos do Livro
            txtAutor.setText("");
            txtNumPaginas.setText("");

            // limpa campos da Revista
            txtEdicao.setText("");
            txtMesAno.setText("");

            // limpa campos do DVD
            txtDiretor.setText("");
            txtDuracao.setText("");

        });

        btnVoltar.addActionListener(e -> {
            new MenuBibliotecario(biblioteca);
            dispose();
        });

        setVisible(true);
    }
}