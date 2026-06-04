package org.example;

import java.util.List;
import java.util.ArrayList;

public class Usuario extends Pessoa{

    private List <Emprestimo> historico;
    private int limiteEmprestimo = 3;

    public Usuario(int id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
        this.historico = new ArrayList<>();
    }
    
    public void adicionarEmprestimo (Emprestimo emprestimo){
        historico.add(emprestimo);
    }
    
    public boolean podePegarEmprestimo(){
        
        int emprestimosEmAndamento = 0;
       
        for (Emprestimo emprestimo : historico){
            if(emprestimo.getStatus() == StatusEmprestimo.EM_ANDAMENTO){
                emprestimosEmAndamento++;
            }
        }
        
        if(emprestimosEmAndamento >= limiteEmprestimo) return false;
        
        else return true;
    }
}
