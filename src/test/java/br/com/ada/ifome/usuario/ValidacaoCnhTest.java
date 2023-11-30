package br.com.ada.ifome.usuario;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.exceptions.CnhInvalidoException;
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
public class ValidacaoCnhTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void usuarioCnhInvalidoComLetra() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CNH);
        usuario.setNumeroDeDocumento("4348383435J");
        assertThrows(CnhInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioCnhInvalidoCom12Digitos() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CNH);
        usuario.setNumeroDeDocumento("434838343501");
        assertThrows(CnhInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioCnhInvalidoCom10Digitos() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CNH);
        usuario.setNumeroDeDocumento("1234567890");
        assertThrows(CnhInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioComCnhValido() {
        // Mockar ação de save
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.CNH);
        usuario.setNumeroDeDocumento("43483834350");
        when(usuarioRepository.save(any())).thenReturn(usuario);
        var usuarioSalvo = usuarioService.salvar(usuario);

        assertNotNull(usuarioSalvo);
        // Validar se foi chamado o save do repository
        verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }
}
