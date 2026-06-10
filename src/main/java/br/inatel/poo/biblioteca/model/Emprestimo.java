package br.inatel.poo.biblioteca.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private Usuario usuario;
    private ItemBiblioteca item;
    private LocalDate dataSaida;
    private LocalDate dataPrevista;
    private LocalDate dataRetorno;
    private StatusEmprestimo status;


    public Emprestimo (Usuario usuario, ItemBiblioteca item, LocalDate dataSaida, LocalDate dataPrevista){
        this.usuario = usuario;
        this.item = item;
        this.dataSaida= dataSaida;
        this.dataPrevista = dataPrevista;
        this.dataRetorno = null;
        this.status = StatusEmprestimo.EM_ANDAMENTO;;
    }

    public double calcularMulta(){
        if(dataRetorno == null) return 0;

        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataRetorno);

        if(diasAtraso > 0){
            return diasAtraso * 1.00;
        }
        return 0;
    }

    public void registrarDevolucao(){
        dataRetorno = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataPrevista, dataRetorno);

        if(dias > 0){
            status = StatusEmprestimo.ATRASADO;
        }
        else status = StatusEmprestimo.DEVOLVIDO;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }
}
