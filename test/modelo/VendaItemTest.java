package modelo;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para o método calcularSubtotal da classe VendaItem.
 */
public class VendaItemTest {

    // ---------------------------------------------------------------
    // Testes de casos válidos (devem funcionar sem lançar exceção)
    // ---------------------------------------------------------------

    @Test
    public void testCalcularSubtotal_PrecoEQuantidadeNormais() {
        // 2 CDs de R$ 10,00 = R$ 20,00
        BigDecimal resultado = VendaItem.calcularSubtotal(new BigDecimal("10.00"), 2);
        assertEquals(new BigDecimal("20.00"), resultado);
    }

    @Test
    public void testCalcularSubtotal_QuantidadeUm() {
        // 1 CD de R$ 29,90 = R$ 29,90
        BigDecimal resultado = VendaItem.calcularSubtotal(new BigDecimal("29.90"), 1);
        assertEquals(new BigDecimal("29.90"), resultado);
    }

    @Test
    public void testCalcularSubtotal_PrecoDecimal() {
        // 3 CDs de R$ 12,99 = R$ 38,97
        BigDecimal resultado = VendaItem.calcularSubtotal(new BigDecimal("12.99"), 3);
        assertEquals(new BigDecimal("38.97"), resultado);
    }

    @Test
    public void testCalcularSubtotal_GrandeQuantidade() {
        // 100 CDs de R$ 5,00 = R$ 500,00
        BigDecimal resultado = VendaItem.calcularSubtotal(new BigDecimal("5.00"), 100);
        assertEquals(new BigDecimal("500.00"), resultado);
    }

    // ---------------------------------------------------------------
    // Testes de casos inválidos (devem lançar IllegalArgumentException)
    // ---------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSubtotal_PrecoNulo_DeveLancarExcecao() {
        // Preço nulo não é permitido
        VendaItem.calcularSubtotal(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSubtotal_PrecoZero_DeveLancarExcecao() {
        // Preço zero não faz sentido para uma venda
        VendaItem.calcularSubtotal(BigDecimal.ZERO, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSubtotal_PrecoNegativo_DeveLancarExcecao() {
        // Preço negativo não é permitido
        VendaItem.calcularSubtotal(new BigDecimal("-5.00"), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSubtotal_QuantidadeZero_DeveLancarExcecao() {
        // Não faz sentido vender 0 CDs
        VendaItem.calcularSubtotal(new BigDecimal("10.00"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSubtotal_QuantidadeNegativa_DeveLancarExcecao() {
        // Quantidade negativa não é permitida
        VendaItem.calcularSubtotal(new BigDecimal("10.00"), -1);
    }
}