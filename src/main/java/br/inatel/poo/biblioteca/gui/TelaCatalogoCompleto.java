package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.model.ItemBiblioteca;
import br.inatel.poo.biblioteca.service.Biblioteca;

import javax.swing.*;
import java.awt.*;

public class TelaCatalogoCompleto extends JFrame {

    private Biblioteca biblioteca;

    public TelaCatalogoCompleto(Biblioteca biblioteca, String origem){
        this.biblioteca =  biblioteca;

        setTitle("Catálogo Completo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Catálogo Completo", SwingConstants.CENTER);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        for (ItemBiblioteca item : biblioteca.getCatalogo().getItens()) {
            areaTexto.append(item.getTitulo() + " | " + item.getClass().getSimpleName() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JButton btnVoltar = new JButton("Voltar");

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        add(painel);

        btnVoltar.addActionListener( e ->{
            if(origem.equals("bibliotecario")) {
                new MenuBibliotecario(biblioteca);
            }
            else{
                new MenuUsuario(biblioteca);
            }
            dispose();
        });

        setVisible(true);
    }
}
