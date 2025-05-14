
package com.pizzaria.util;


public class DescontoPedido {
    public static double aplicarDesconto(double valorTotal, double percentualDesconto) {
        if (valorTotal < 0 || percentualDesconto < 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("Valores inválidos para desconto.");
        }
        return valorTotal - (valorTotal * percentualDesconto / 100);
    }
}
