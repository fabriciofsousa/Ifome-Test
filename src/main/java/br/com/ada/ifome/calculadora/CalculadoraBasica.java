package br.com.ada.ifome.calculadora;

public class CalculadoraBasica {

    public Double somar(Double x, Double y) {
        return x + y;
    }

    public Double dividir(Double x, Double y) {
        if (y == 0) {
            throw new ArithmeticException();
        }

        return x / y;
    }

    public Double subtrair(Double x, Double y) {
        return x - y;
    }
}
