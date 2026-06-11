package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.model.*;
import br.inatel.poo.biblioteca.service.Biblioteca;
import br.inatel.poo.biblioteca.persistence.GerenciadorArquivos;


import javax.swing.*;
import java.awt.*;


public class MenuBibliotecario extends JFrame {

    private Biblioteca biblioteca;

    public MenuBibliotecario(Biblioteca biblioteca){
        this.biblioteca = biblioteca;

        setTitle("Menu Bibliotecário: ");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao Menu Bibliotecário", SwingConstants.CENTER);

        JButton btnCadastrarItem = new JButton("Cadastrar Item");
        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        JButton btnListarCatalogo = new JButton("Listar Catálogo");
        JButton btnRemoverItem = new JButton("Remover Item");
        JButton btnVoltar = new JButton("Voltar");

        btnCadastrarItem.setFocusable(false);
        btnCadastrarUsuario.setFocusable(false);
        btnListarCatalogo.setFocusable(false);
        btnRemoverItem.setFocusable(false);
        btnVoltar.setFocusable(false);

        painel.add(titulo);
        painel.add(btnCadastrarItem);
        painel.add(btnCadastrarUsuario);
        painel.add(btnListarCatalogo);
        painel.add(btnRemoverItem);
        painel.add(btnVoltar);

        add(painel);

        btnCadastrarItem.addActionListener(e -> {
            new TelaCadastroItem(biblioteca);
            dispose();
        });

        btnCadastrarUsuario.addActionListener(e -> {
            new TelaCadastroUsuario(biblioteca);
            dispose();
        });

        btnListarCatalogo.addActionListener(e -> {
            new TelaCatalogoCompleto(biblioteca, "bibliotecario");
            dispose();
        });

        btnVoltar.addActionListener(e ->{
            new TelaPrincipal(biblioteca);
            dispose();
        });

        btnRemoverItem.addActionListener( e ->{
            new TelaRemoverItem(biblioteca);
            dispose();
        });

        setVisible(true);
    }


}
