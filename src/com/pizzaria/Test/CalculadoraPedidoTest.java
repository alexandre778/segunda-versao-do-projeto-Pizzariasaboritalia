
package com.pizzaria.Test;


import com.pizzaria.util.CalculadoraPedido;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;



public class CalculadoraPedidoTest {

    @Test
    public void testCalculoPrecoTotalValido() {
        double resultado = CalculadoraPedido.calcularPrecoTotal(25.0, 3);
        assertEquals(75.0, resultado, 0.01);
    }

    @Test
    public void testPrecoZero() {
        double resultado = CalculadoraPedido.calcularPrecoTotal(0.0, 5);
        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    public void testQuantidadeZero() {
        double resultado = CalculadoraPedido.calcularPrecoTotal(50.0, 0);
        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    public void testEntradaNegativaPreco() {
        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraPedido.calcularPrecoTotal(-10.0, 2);
        });
    }

    @Test
    public void testEntradaNegativaQuantidade() {
        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraPedido.calcularPrecoTotal(10.0, -2);
        });
    }

    @Test
    public void testValorDecimalGrande() {
        double resultado = CalculadoraPedido.calcularPrecoTotal(99.99, 2);
        assertEquals(199.98, resultado, 0.01);
    }
}

