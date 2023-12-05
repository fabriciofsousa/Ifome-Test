package br.com.ada.ifome.veiculo;

import br.com.ada.ifome.exceptions.VeiculoDataModeloInvalidoException;
import br.com.ada.ifome.exceptions.VeiculoInvalidoException;
import br.com.ada.ifome.exceptions.VeiculoPlacaVeiculoInvalidoException;
import br.com.ada.ifome.exceptions.VeiculoRenavamInvalidoException;
import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.repository.VeiculoRepository;
import br.com.ada.ifome.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VeiculoTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;
    @BeforeEach
    public void setup(){
        veiculo = new Veiculo();
        veiculo.setDataModelo(Calendar.getInstance());
        veiculo.setPlaca("ZZZ9999");
        veiculo.setRenavam("37759184770");
    }

    @Test
    public void veiculoOKTest() {
        assertDoesNotThrow(() -> veiculoService.salvar(veiculo));
    }

    @Test
    public void veiculoNullThrowsExceptionTest() {
        assertThrows(VeiculoInvalidoException.class, () -> veiculoService.salvar(null));
    }

    @Test
    public void placaVeiculoNullThrowsExceptionTest(){
        veiculo.setPlaca(null);
        assertThrows(VeiculoPlacaVeiculoInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void placaVeiculoVazioThrowsExceptionTest(){
        veiculo.setPlaca("");
        assertThrows(VeiculoPlacaVeiculoInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void placaVeiculoInvalidaThrowsExceptionTest(){
        veiculo.setPlaca("999ZZZZ");
        assertThrows(VeiculoPlacaVeiculoInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void dataModeloNullThrowsExceptionTest(){
        veiculo.setDataModelo(null);
        assertThrows(VeiculoDataModeloInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void dataModeloMenorQue2010ThrowsExceptionTest(){
        Calendar anoMenor2010 = Calendar.getInstance();
        anoMenor2010.set(Calendar.YEAR, 2009);
        anoMenor2010.set(Calendar.MONTH, Calendar.DECEMBER);
        anoMenor2010.set(Calendar.DAY_OF_MONTH, 31);
        veiculo.setDataModelo(anoMenor2010);
        assertThrows(VeiculoDataModeloInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void renavamNullThrowsExceptionTest(){
        veiculo.setRenavam(null);
        assertThrows(VeiculoRenavamInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void renavamVazioThrowsExceptionTest(){
        veiculo.setRenavam("");
        assertThrows(VeiculoRenavamInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }

    @Test
    public void renavamInvalidoThrowsExceptionTest(){
        veiculo.setRenavam("99999999999");
        assertThrows(VeiculoRenavamInvalidoException.class, () -> veiculoService.salvar(veiculo));
    }
}
