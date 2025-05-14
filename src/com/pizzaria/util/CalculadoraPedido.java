package com.pizzaria.util;


public class CalculadoraPedido {
    public static double calcularPrecoTotal(double precoUnitario, int quantidade) {
        if (precoUnitario < 0 || quantidade < 0) {
            throw new IllegalArgumentException("Preço e quantidade não podem ser negativos.");
        }
        return precoUnitario * quantidade;
    }
}
