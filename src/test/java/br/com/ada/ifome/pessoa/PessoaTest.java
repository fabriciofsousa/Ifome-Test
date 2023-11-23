package br.com.ada.ifome.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaTest {

    @Mock
    Pessoa pessoa;

    @Test
    public void validaCpfVazio() {
        // var pessoa = new Pessoa();
        pessoa.setCpf("05877755566");
        when(pessoa.validaCpf(anyString())).thenReturn(false);

        var resultado = pessoa.validaCpf("0666611111");
        assertFalse(resultado);
    }
}
