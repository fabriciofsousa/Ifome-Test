package br.com.ada.ifome.calculadora;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

public class CalculadoraBasicaTest {

    CalculadoraBasica calculadora;

    @BeforeEach
    public void executaInicio() {
        System.out.println("Iniciando objeto");
        calculadora = new CalculadoraBasica();
    }

    @Test
    @DisplayName("Somar os números inteiros com resultado igual a 8")
    public void somaNumeroInteiro() {
        var resultado = calculadora.somar(3d, 5d);
        assertEquals(8, resultado);
    }

    @Test
    @RepeatedTest(2)
    public void somaNumeroDecimal() {
        var resultado = calculadora.somar(3.5, 3.5);
        assertEquals(7, resultado);
    }

    @Test
    public void subtrairNumeroInteiro() {
        var resultado = calculadora.subtrair(8d, 5d);
        assertEquals(3, resultado);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 3.5d, 4.5d })
    void somarESubtrair(Double valor) {
        var resultado = calculadora.somar(5d, valor);
        var resultadoSubtracao = calculadora.subtrair(5d, valor);
        assertTrue(resultado > 8);
        assertTrue(resultadoSubtracao > 0);
    }

    @ParameterizedTest
    @CsvSource({"6.5, 5.5", "8.5, 5.5"})
    @DisabledOnOs(MAC)
    void somarESubtrair(Double x, Double y) {
        var resultado = calculadora.somar(x, y);
        var resultadoSubtracao = calculadora.subtrair(x, y);
        assertTrue(resultado > 5);
        assertTrue(resultadoSubtracao > 0);
    }

    @Test
    @DisplayName("Dividir o valor de 5 com 0")
    public void dividirNumeroPorZero() {
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(5d, 0d));
    }
}
