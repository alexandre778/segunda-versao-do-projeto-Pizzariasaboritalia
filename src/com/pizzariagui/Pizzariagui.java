package com.pizzariagui;

import javax.swing.SwingUtilities;

public class Pizzariagui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true); // Inicia a tela de login
            }
        });
    }

}
