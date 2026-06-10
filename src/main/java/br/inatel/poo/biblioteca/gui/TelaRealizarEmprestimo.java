package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.exception.ItemIndisponivelException;
import br.inatel.poo.biblioteca.exception.LimiteEmprestimoException;
import br.inatel.poo.biblioteca.model.ItemBiblioteca;
import br.inatel.poo.biblioteca.model.Usuario;
import br.inatel.poo.biblioteca.service.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaRealizarEmprestimo extends JFrame {

    private Biblioteca biblioteca;

    public TelaRealizarEmprestimo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Realizar Empréstimo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Itens Disponíveis", SwingConstants.CENTER);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        for (ItemBiblioteca item : biblioteca.getCatalogo().listarDisponiveis()) {
            areaTexto.append("ID: " + item.getId() + " | " + item.getTitulo() + " | " + item.getClass().getSimpleName() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblIdUsuario = new JLabel("ID do Usuário:");
        JTextField txtIdUsuario = new JTextField();

        JLabel lblIdItem = new JLabel("ID do Item:");
        JTextField txtIdItem = new JTextField();

        JLabel lblDataPrevista = new JLabel("Prazo (dias):");
        JTextField txtPrazo = new JTextField();

        JButton btnEmprestimo = new JButton("Realizar Empréstimo");
        JButton btnVoltar = new JButton("Voltar");

        painelFormulario.add(lblIdUsuario);
        painelFormulario.add(txtIdUsuario);
        painelFormulario.add(lblIdItem);
        painelFormulario.add(txtIdItem);
        painelFormulario.add(lblDataPrevista);
        painelFormulario.add(txtPrazo);
        painelFormulario.add(btnEmprestimo);
        painelFormulario.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelFormulario, BorderLayout.SOUTH);

        add(painel);

        btnEmprestimo.addActionListener(e -> {
            try {
                int idUsuario = Integer.parseInt(txtIdUsuario.getText());
                int idItem = Integer.parseInt(txtIdItem.getText());
                int prazo = Integer.parseInt(txtPrazo.getText());

                Usuario usuario = biblioteca.buscarUsuario(idUsuario);
                ItemBiblioteca item = biblioteca.getCatalogo().buscarItemPorId(idItem);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
                    return;
                }

                if (item == null) {
                    JOptionPane.showMessageDialog(this, "Item não encontrado!");
                    return;
                }

                LocalDate dataPrevista = LocalDate.now().plusDays(prazo);
                biblioteca.realizarEmprestimo(usuario, item, dataPrevista);
                JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");

                // atualiza a lista de disponíveis
                areaTexto.setText("");
                for (ItemBiblioteca i : biblioteca.getCatalogo().listarDisponiveis()) {
                    areaTexto.append("ID: " + i.getId() + " | " + i.getTitulo() + " | " + i.getClass().getSimpleName() + "\n");
                }

            } catch (ItemIndisponivelException | LimiteEmprestimoException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!");
            }
        });

        btnVoltar.addActionListener(e -> {
            new MenuUsuario(biblioteca);
            dispose();
        });

        setVisible(true);
    }
}