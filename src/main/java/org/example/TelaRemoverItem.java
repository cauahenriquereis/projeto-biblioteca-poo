package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaRemoverItem extends JFrame {

    private Biblioteca biblioteca;

    public TelaRemoverItem (Biblioteca biblioteca){

        this.biblioteca = biblioteca;

        setTitle("Remover Item");
        setSize(600 ,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Itens do catálogo: ", SwingConstants.CENTER);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        for (ItemBiblioteca item : biblioteca.getCatalogo().getItens()) {
            areaTexto.append("ID Item: " + item.getId() + " | Item: " + item.getTitulo() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JPanel painelFormulario = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel lblIdItem = new JLabel("ID do Item a remover:");
        JTextField txtIdItem = new JTextField();

        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        painelFormulario.add(lblIdItem);
        painelFormulario.add(txtIdItem);
        painelFormulario.add(btnRemover);
        painelFormulario.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelFormulario, BorderLayout.SOUTH);

        add(painel);

        btnRemover.addActionListener(e -> {

            int idItem = Integer.parseInt(txtIdItem.getText());

            biblioteca.getCatalogo().removerItem(idItem);

            JOptionPane.showMessageDialog(this, "Remoção realizada com sucesso!");

            // limpa campo ID
            txtIdItem.setText("");

            GerenciadorArquivos.salvarCatalogo(biblioteca.getCatalogo());

            // atualiza a lista do catálogo
            areaTexto.setText("");
            for (ItemBiblioteca item : biblioteca.getCatalogo().getItens()) {
                areaTexto.append("ID Item: " + item.getId() + " | Item: " + item.getTitulo() + "\n");
            }


        });

        btnVoltar.addActionListener(e -> {
            new MenuBibliotecario(biblioteca);
            dispose();
        });

        setVisible(true);

    }
}
