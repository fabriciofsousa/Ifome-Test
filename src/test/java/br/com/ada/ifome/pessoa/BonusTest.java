package br.com.ada.ifome.pessoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusTest {

    @Test
    @DisplayName("Funcionario recebe 5 porcento de bonus vendendo 21 vitaminas e 6 xaropes com salario de 1000")
    public void deveReceber5PorcentoBonus() {
        var bonus = new Bonus();
        double bonusRecebido = bonus.calcularBonus(21, 6, 1000);
        assertEquals(bonusRecebido, 50);
    }

    @Test
    @DisplayName("Funcionario vendeu 31 vitaminas e 12 xaropes com salario de 1000")
    public void deveReceber10PorcentoBonus() {
        var bonus = new Bonus();
        double bonusRecebido = bonus.calcularBonus(31, 12, 1000);
        assertEquals(bonusRecebido, 100);
    }

    @Test
    @DisplayName("Funcionario vendeu 5 vitaminas e 2 xaropes com salario de 1000")
    public void naoRecebeBonus() {
        var bonus = new Bonus();
        double bonusRecebido = bonus.calcularBonus(5, 2, 1000);
        assertEquals(bonusRecebido, 0);
    }
}
