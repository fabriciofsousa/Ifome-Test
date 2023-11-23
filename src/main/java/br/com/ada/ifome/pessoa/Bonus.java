package br.com.ada.ifome.pessoa;

public class Bonus {
    public double calcularBonus(int vitaminas, int xaropes, double salario) {
        double bonus = 0;
        if (vitaminas > 20 && xaropes > 5) {
            bonus = 0.05;
        }

        if (vitaminas > 30 && xaropes > 10) {
            bonus = 0.10;
        }

        return salario * bonus;
    }
}
