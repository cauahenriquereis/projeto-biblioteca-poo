package org.example;

import javax.swing.*;

public class TelaCatalogoCompleto extends JFrame {

    private Biblioteca biblioteca;

    public TelaCatalogoCompleto(Biblioteca biblioteca){
        this.biblioteca =  biblioteca;
        setVisible(true);
    }
}
