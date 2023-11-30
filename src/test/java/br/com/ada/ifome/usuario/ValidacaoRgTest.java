package br.com.ada.ifome.usuario;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.exceptions.RgInvalidoException;
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
public class ValidacaoRgTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    public void usuarioRgInvalidoComLetra() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.RG);
        usuario.setNumeroDeDocumento("44137792H");
        assertThrows(RgInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioRgInvalidoCom8Digitos() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.RG);
        usuario.setNumeroDeDocumento("4413779200");
        assertThrows(RgInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioRgInvalidoCom6Digitos() {
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.RG);
        usuario.setNumeroDeDocumento("123456");
        assertThrows(RgInvalidoException.class, () -> usuarioService.salvar(usuario));
    }

    @Test
    public void usuarioComRgValido() {
        // Mockar ação de save
        var usuario = new Usuario();
        usuario.setTipoDocumento(TipoDeDocumento.RG);
        usuario.setNumeroDeDocumento("4413779");
        when(usuarioRepository.save(any())).thenReturn(usuario);
        var usuarioSalvo = usuarioService.salvar(usuario);

        assertNotNull(usuarioSalvo);
        // Validar se foi chamado o save do repository
        verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }
}
