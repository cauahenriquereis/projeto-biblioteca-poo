package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Biblioteca biblioteca = new Biblioteca(catalogo);
        new TelaPrincipal(biblioteca);

    }
}