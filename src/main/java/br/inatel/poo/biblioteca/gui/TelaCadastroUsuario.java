package br.inatel.poo.biblioteca.gui;

import br.inatel.poo.biblioteca.model.Usuario;
import br.inatel.poo.biblioteca.service.Biblioteca;

import javax.swing.*;
import java.awt.GridLayout;

public class TelaCadastroUsuario extends JFrame {

    private Biblioteca biblioteca;

    public TelaCadastroUsuario(Biblioteca biblioteca){
        this.biblioteca = biblioteca;

        setTitle("Cadastrar Usuário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblCpf);
        painel.add(txtCpf);
        painel.add(lblTelefone);
        painel.add(txtTelefone);
        painel.add(btnCadastrar);
        painel.add(btnVoltar);


        add(painel);

        btnCadastrar.addActionListener(e -> {
            int id = biblioteca.gerarIdUsuario();
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String telefone = txtTelefone.getText();

            Usuario usuario = new Usuario(id, nome, cpf, telefone);
            biblioteca.cadastrarUsuario(usuario);

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");

            txtNome.setText("");
            txtCpf.setText("");
            txtTelefone.setText("");
        });

        btnVoltar.addActionListener(e -> {
            new MenuBibliotecario(biblioteca);
            dispose();
        });
        setVisible(true);
    }
}
