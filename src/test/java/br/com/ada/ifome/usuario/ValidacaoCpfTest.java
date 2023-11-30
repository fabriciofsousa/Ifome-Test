package br.com.ada.ifome.usuario;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.exceptions.CpfInvalidoException;
import br.com.ada.ifome.repository.UsuarioRepository;
import br.com.ada.ifome.service.UsuarioService;
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
public class ValidacaoCpfTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    public void usuarioCpfInvalidoComLetra() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CPF);
        usuario.setNumeroDeDocumento("1234567891e");
        assertThrows(CpfInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioCpfInvalidoCom12Digitos() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CPF);
        usuario.setNumeroDeDocumento("123456789123");
        assertThrows(CpfInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioComCpfValido() {
        // Mockar ação de save
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CPF);
        usuario.setNumeroDeDocumento("04455566633");
        when(usuarioRepository.save(any())).thenReturn(usuario);
        var usuarioSalvo = usuarioService.salvar(usuario);

        assertNotNull(usuarioSalvo);
        // Validar se foi chamado o save do repository
        verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }

}
