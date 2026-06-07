package org.example;

import javax.swing.*;
import java.awt.*;

public class MenuUsuario extends JFrame {

    private Biblioteca biblioteca;

    public MenuUsuario (Biblioteca biblioteca){
        this.biblioteca = biblioteca;

        setTitle("Menu Usuário: ");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao Menu Usuário", SwingConstants.CENTER);

        JButton btnRealizarEmprestimo = new JButton("Realizar Empréstimo");
        JButton btnRealizarDevolucao = new JButton("Realizar Devolução");
        JButton btnListarCatalogo = new JButton("Listar Catálogo");
        JButton btnBuscarItem = new JButton("Buscar Item");

        painel.add(titulo);
        painel.add(btnRealizarEmprestimo);
        painel.add(btnRealizarDevolucao);
        painel.add(btnListarCatalogo);
        painel.add(btnBuscarItem);

        add(painel);

        btnRealizarEmprestimo.addActionListener(e -> {
            new TelaRealizarEmprestimo(biblioteca);
            dispose();
        });

        btnRealizarDevolucao.addActionListener(e -> {
            new TelaRealizarDevolucao(biblioteca);
            dispose();
        });

        btnListarCatalogo.addActionListener(e -> {
            new TelaCatalogoCompleto(biblioteca);
            dispose();
        });

        btnBuscarItem.addActionListener(e -> {
            new TelaBuscarItem(biblioteca);
            dispose();
        });


        setVisible(true);
    }


}
