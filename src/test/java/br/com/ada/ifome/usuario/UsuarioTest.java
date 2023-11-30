package br.com.ada.ifome.usuario;

import br.com.ada.ifome.exceptions.UsuarioInvalidoException;
import br.com.ada.ifome.repository.UsuarioRepository;
import br.com.ada.ifome.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UsuarioTest {

    // 1 - Passar usuário null
    // (Falhar) -> Esperar uma exception de usuario invalido

    // 2 - Passar cpf incorreto: 123456789123
    // 3 - Passar cpf incorreto: 1234567891e
    // (Validar se a exception CpfInvalidoException foi chamada)

    // 3 - Passar cpf valido: 12345678912 (Sucesso)

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testaUsuarioNull() {
        // 1. chamar o service passando referência null
        assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(null));
    }
}
