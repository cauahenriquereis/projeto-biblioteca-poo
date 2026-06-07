package org.example;

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

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao Menu Bibliotecário", SwingConstants.CENTER);

        JButton btnCadastrarItem = new JButton("Cadastrar Item");
        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        JButton btnListarCatalogo = new JButton("Listar Catálogo");

        painel.add(titulo);
        painel.add(btnCadastrarItem);
        painel.add(btnCadastrarUsuario);
        painel.add(btnListarCatalogo);

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
            new TelaCatalogoCompleto(biblioteca);
            dispose();
        });

        setVisible(true);
    }

}
