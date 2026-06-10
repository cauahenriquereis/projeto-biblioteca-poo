package br.inatel.poo.biblioteca.service;

import br.inatel.poo.biblioteca.model.ItemBiblioteca;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private List<ItemBiblioteca> itens;

   public Catalogo (){
       this.itens = new ArrayList<>();
   }

   public void adicionarItem(ItemBiblioteca item){
       itens.add(item);
   }

   public void removerItem(int id){
       for(ItemBiblioteca item: itens){
           if(item.getId() == id){
               itens.remove(item);
               break;
           }
       }
   }

   public List<ItemBiblioteca> buscarPorTitulo(String titulo){

       List<ItemBiblioteca> buscaTitulo = new ArrayList<>();

       for(ItemBiblioteca item:itens){
           if(item.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
               buscaTitulo.add(item);
           }

       }
       return buscaTitulo;
   }

   public List<ItemBiblioteca> listarDisponiveis(){

       List <ItemBiblioteca> listaDisponiveis = new ArrayList<>();

       for(ItemBiblioteca item : itens){
           if(item.isDisponivel()) {
               listaDisponiveis.add(item);
           }
       }

       return listaDisponiveis;
   }

    public ItemBiblioteca buscarItemPorId(int id) {
        for (ItemBiblioteca item : itens) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

   public void listarTodos(){
       for(ItemBiblioteca item : itens){
           item.exibirDetalhes();
       }
   }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }
}
