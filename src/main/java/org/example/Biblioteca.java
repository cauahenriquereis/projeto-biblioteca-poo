package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca {

    private Catalogo catalogo;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimosAtivos;

    public Biblioteca(Catalogo catalogo){
        this.catalogo = catalogo;
        this.usuarios = new ArrayList<>();
        this.emprestimosAtivos = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(int id){

        for(Usuario usuario : usuarios)
        {
            if(usuario.getId() == id){
               return usuario;
            }
        }
        return null;
    }

    public void realizarEmprestimo(Usuario usuario, ItemBiblioteca item, LocalDate dataPrevista){

        // if (!item.isDisponivel()) lançar ItemIndisponivelException

        if(!usuario.podePegarEmprestimo()){
            //lançar LimiteEmprestimoException;
        }

        Emprestimo emprestimo = new Emprestimo(usuario, item, LocalDate.now(), dataPrevista);

        usuario.adicionarEmprestimo(emprestimo);


        item.setDisponivel(false);

        emprestimosAtivos.add(emprestimo);

        }



    public void realizarDevolucao(Emprestimo emprestimo){

        emprestimo.registrarDevolucao();

        ItemBiblioteca itemEmprestado = emprestimo.getItem();
        itemEmprestado.setDisponivel(true);

        emprestimosAtivos.remove(emprestimo);
    }
}
