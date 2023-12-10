package br.com.ada.ifome.usuario;

import br.com.ada.ifome.enumeration.InstituicaoBancaria;
import br.com.ada.ifome.enumeration.TipoConta;
import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.ContaBancaria;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.exceptions.usuario.RgInvalidoException;
import br.com.ada.ifome.model.documents.CNH;
import br.com.ada.ifome.model.documents.Documento;
import br.com.ada.ifome.repository.UsuarioRepository;
import br.com.ada.ifome.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

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

        LocalDate dataExpedicao = LocalDate.of(2030, Month.FEBRUARY, 28);
        Documento cnh = new CNH();
        cnh.setDataExpedicao(dataExpedicao);

        usuario.setDocumentos(Arrays.asList(cnh));
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        contaBancaria.setTipoConta(TipoConta.CONTA_CORRENTE);
        contaBancaria.setInstituicaoBancaria(InstituicaoBancaria.SANTANDER);

        usuario.setContasBancarias(Arrays.asList(contaBancaria));
        var usuarioSalvo = usuarioService.salvar(usuario);

        assertNotNull(usuarioSalvo);
        // Validar se foi chamado o save do repository
        verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }
}
