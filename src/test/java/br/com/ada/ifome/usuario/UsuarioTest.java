package br.com.ada.ifome.usuario;

import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void usuarioCpfInvalidoComLetra() {
        var usuario = new Usuario();
        usuario.setCpf("1234567891e");
        assertThrows(CpfInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioCpfInvalidoCom12Digitos() {
        var usuario = new Usuario();
        usuario.setCpf("123456789123");
        assertThrows(CpfInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioComCpfValido() {
        // Mockar ação de save
        var usuario = new Usuario();
        usuario.setCpf("04455566633");
        when(usuarioRepository.save(any())).thenReturn(usuario);
        var usuarioSalvo = usuarioService.salvar(usuario);

        assertNotNull(usuarioSalvo);
        // Validar se foi chamado o save do repository
        verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }
}
