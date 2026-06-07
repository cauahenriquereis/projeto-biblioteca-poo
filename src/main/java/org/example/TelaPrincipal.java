package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private Biblioteca biblioteca;

    public TelaPrincipal(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Sistema de Biblioteca: ");

        setSize(400, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao Sistema de Biblioteca", SwingConstants.CENTER);

        JButton btnBibliotecario = new JButton("Bibliotecário");
        JButton btnUsuario = new JButton("Usuário");

        painel.add(titulo);
        painel.add(btnBibliotecario);
        painel.add(btnUsuario);

        add(painel);

        btnBibliotecario.addActionListener(e -> {
            new MenuBibliotecario(biblioteca);
            dispose();
        });

        btnUsuario.addActionListener(e -> {
            new MenuUsuario(biblioteca);
            dispose();
        });

        setVisible(true);
    }
}
