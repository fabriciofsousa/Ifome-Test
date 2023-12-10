package br.com.ada.ifome.usuario;

import br.com.ada.ifome.enumeration.InstituicaoBancaria;
import br.com.ada.ifome.enumeration.TipoConta;
import br.com.ada.ifome.exceptions.usuario.ContaBancariaException;
import br.com.ada.ifome.model.ContaBancaria;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.repository.UsuarioRepository;
import br.com.ada.ifome.service.UsuarioService;
import br.com.ada.ifome.util.ValidaUsuarioUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ContaBancariaTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testContaBancariaValida() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(5678);
        contaBancaria.setTipoConta(TipoConta.CONTA_CORRENTE);
        contaBancaria.setInstituicaoBancaria(InstituicaoBancaria.BANCO_DO_BRASIL);

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        assertDoesNotThrow(() -> ValidaUsuarioUtil.validarContaBancaria(usuario));
    }

    @Test
    public void testContaNula() {
        Usuario usuario = new Usuario();
        usuario.setContasBancarias(null);

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Conta não informada!", exception.getMessage());
    }

    @Test
    public void testNumeroAgenciaInvalido() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(0L); // Número de agência inválido

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Numero da agência não pode ser zero ou negativa!", exception.getMessage());
    }

    @Test
    public void testNumeroAgenciaInvalidoNull() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(null); // Número de agência inválido

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Numero da agência não pode ser zero ou negativa!", exception.getMessage());
    }

    @Test
    public void testNumeroContaInvalido() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroConta(0); // Número de conta inválido
        contaBancaria.setNumeroAgencia(1234L);
        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Numero da contas não pode ser zero ou negativa!", exception.getMessage());
    }

    @Test
    public void testNumeroContaInvalidoNull() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(null); // Número de conta inválido

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Numero da contas não pode ser zero ou negativa!", exception.getMessage());
    }

    @Test
    public void testTipoContaInvalido() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setTipoConta(null); // Tipo de conta inválido
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Tipo de conta não informada!", exception.getMessage());
    }

    @Test
    public void testTipoContaEnumInvalido() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.setTipoConta(TipoConta.valueOf("999")));
        assertEquals("No enum constant br.com.ada.ifome.enumeration.TipoConta.999", exception.getMessage());
    }

    @Test
    public void testInstituicaoBancariaInvalida() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        contaBancaria.setTipoConta(TipoConta.CONTA_CORRENTE);
        contaBancaria.setInstituicaoBancaria(null); // Instituição bancária inválida

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ContaBancariaException exception = assertThrows(ContaBancariaException.class,
                () -> ValidaUsuarioUtil.validarContaBancaria(usuario));
        assertEquals("Instituicao Bancaria não informada!", exception.getMessage());
    }

    @Test
    public void testInstituicaoBancariaEnumInvalida() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        contaBancaria.setTipoConta(TipoConta.CONTA_CORRENTE);


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.setInstituicaoBancaria(InstituicaoBancaria.valueOf("999")));
        assertEquals("No enum constant br.com.ada.ifome.enumeration.InstituicaoBancaria.999", exception.getMessage());
    }

    @Test
    public void testInstituicaoBancariaValida() {
        Usuario usuario = new Usuario();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setNumeroAgencia(1234L);
        contaBancaria.setNumeroConta(456);
        contaBancaria.setTipoConta(TipoConta.CONTA_CORRENTE);
        contaBancaria.setInstituicaoBancaria(InstituicaoBancaria.SANTANDER);

        usuario.setContasBancarias(Arrays.asList(contaBancaria));

        ValidaUsuarioUtil.validarContaBancaria(usuario);
        assertDoesNotThrow(() -> ValidaUsuarioUtil.validarContaBancaria(usuario));
    }

}
