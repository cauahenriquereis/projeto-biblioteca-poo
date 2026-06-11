package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.model.Emprestimo;
import br.inatel.poo.biblioteca.service.Biblioteca;
import br.inatel.poo.biblioteca.persistence.GerenciadorArquivos;

import javax.swing.*;
import java.awt.*;

public class TelaRealizarDevolucao extends JFrame {

    private Biblioteca biblioteca;

    public TelaRealizarDevolucao (Biblioteca biblioteca){

        this.biblioteca = biblioteca;

        setTitle("Realizar Devolução");
        setSize(600 ,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Empréstimos ativos: ", SwingConstants.CENTER);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        for (Emprestimo emprestimo : biblioteca.getEmprestimosAtivos()) {
            areaTexto.append("ID Item: " + emprestimo.getItem().getId() + " | Item: " + emprestimo.getItem().getTitulo() + " | Usuário: " + emprestimo.getUsuario().getNome() + " | Status: " + emprestimo.getStatus() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JPanel painelFormulario = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel lblIdItem = new JLabel("ID do Item a devolver:");
        JTextField txtIdItem = new JTextField();

        JButton btnDevolucao = new JButton("Realizar Devolução");
        JButton btnVoltar = new JButton("Voltar");

        painelFormulario.add(lblIdItem);
        painelFormulario.add(txtIdItem);
        painelFormulario.add(btnDevolucao);
        painelFormulario.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelFormulario, BorderLayout.SOUTH);

        add(painel);

        btnDevolucao.addActionListener(e -> {

                    int idItem = Integer.parseInt(txtIdItem.getText().trim());

                    Emprestimo emprestimo = biblioteca.buscarEmprestimoPorId(idItem);

                    biblioteca.realizarDevolucao(emprestimo);

                    GerenciadorArquivos.salvarEmprestimos(biblioteca.getEmprestimosAtivos());

                    double multa = emprestimo.calcularMulta();

                    if (multa > 0) {
                        JOptionPane.showMessageDialog(this, "Devolução realizada! Multa: R$ " + multa);
                    } else {
                        JOptionPane.showMessageDialog(this, "Devolução realizada com sucesso!");
                    }


                    areaTexto.setText("");
                    for (Emprestimo emprestimo1 : biblioteca.getEmprestimosAtivos()) {
                        areaTexto.append("ID Item: " + emprestimo1.getItem().getId() + " | Item: " + emprestimo1.getItem().getTitulo() + " | Usuário: " + emprestimo1.getUsuario().getNome() + " | Status: " + emprestimo1.getStatus() + "\n");
                    }


                });

        btnVoltar.addActionListener(e -> {
            new MenuUsuario(biblioteca);
            dispose();
        });

        setVisible(true);

    }
}
