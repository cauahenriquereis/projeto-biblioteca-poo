package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaBuscarItem extends JFrame {

    private Biblioteca biblioteca;

    public TelaBuscarItem(Biblioteca biblioteca){
        this.biblioteca = biblioteca;

        setTitle("Buscar Item");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel titulo = new JLabel("Buscar Item", SwingConstants.CENTER);

        JPanel painelBusca = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        painelBusca.add(lblTitulo);
        painelBusca.add(txtTitulo);
        painelBusca.add(btnBuscar);

        painelTopo.add(titulo);
        painelTopo.add(painelBusca);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JButton btnVoltar = new JButton("Voltar");

        painel.add(painelTopo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        add(painel);

        btnBuscar.addActionListener(e -> {
            areaTexto.setText("");
            String termoBusca = txtTitulo.getText();

            for (ItemBiblioteca item : biblioteca.getCatalogo().buscarPorTitulo(termoBusca)) {
                areaTexto.append(item.getTitulo() + " | " + item.getClass().getSimpleName() + "\n");
            }
        });

        btnVoltar.addActionListener(e -> {
            new MenuUsuario(biblioteca);
            dispose();
        });

        setVisible(true);
    }

}
