
package com.pizzaria.test;


import com.pizzaria.util.DescontoPedido;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class DescontoPedidoTest {

    @Test
    public void testDescontoValido() {
        double resultado = DescontoPedido.aplicarDesconto(100.0, 10.0);
        assertEquals(90.0, resultado, 0.01);
    }

    @Test
    public void testDescontoZero() {
        double resultado = DescontoPedido.aplicarDesconto(100.0, 0.0);
        assertEquals(100.0, resultado, 0.01);
    }

    @Test
    public void testDescontoMaximo() {
        double resultado = DescontoPedido.aplicarDesconto(100.0, 100.0);
        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    public void testValorTotalNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            DescontoPedido.aplicarDesconto(-50.0, 10.0);
        });
    }

    @Test
    public void testDescontoMaiorQue100() {
        assertThrows(IllegalArgumentException.class, () -> {
            DescontoPedido.aplicarDesconto(100.0, 150.0);
        });
    }
}
