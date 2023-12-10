package br.com.ada.ifome.util;

import br.com.ada.ifome.enumeration.InstituicaoBancaria;
import br.com.ada.ifome.enumeration.TipoConta;
import br.com.ada.ifome.exceptions.usuario.ContaBancariaException;
import br.com.ada.ifome.exceptions.usuario.DataExpedicaoInvalida;
import br.com.ada.ifome.exceptions.usuario.UsuarioInvalidoException;
import br.com.ada.ifome.model.Usuario;

public class ValidaUsuarioUtil {

    public static void validaUsuario(Usuario usuario) {
        validaUsuarioSeNull(usuario);
        validaTipoDocumento(usuario);
        validaDataExpedicao(usuario);
        validarContaBancaria(usuario);
    }

    public static void validarContaBancaria(Usuario usuario) {
        if (usuario.getContasBancarias() == null) {
            throw new ContaBancariaException("Conta não informada!");
        }

        for (var contaBancaria : usuario.getContasBancarias()) {

            // Validação do número da agência
            if (contaBancaria.getNumeroAgencia() == null || contaBancaria.getNumeroAgencia() <= 0) {
                throw new ContaBancariaException("Numero da agência não pode ser zero ou negativa!");
            }

            // Validação do número da conta
            if (contaBancaria.getNumeroConta() == null || contaBancaria.getNumeroConta() <= 0) {
                throw new ContaBancariaException("Numero da contas não pode ser zero ou negativa!");
            }

            // Validação do tipo de conta
            if (contaBancaria.getTipoConta() == null) {
                throw new ContaBancariaException("Tipo de conta não informada!");
            }
            // Validação do tipo de conta
            if (!TipoConta.validarCodigo(contaBancaria.getTipoConta().getCodigo())) {
                throw new ContaBancariaException("Tipo de conta inválida!");
            }

            // Validação do Instituicao Bancaria -
            if (contaBancaria.getInstituicaoBancaria() == null) {
                throw new ContaBancariaException("Instituicao Bancaria não informada!");
            }

            // Validação da instituição bancária teste
            if (!InstituicaoBancaria.validarCodigo(contaBancaria.getInstituicaoBancaria().getCodigo())) {
                throw new ContaBancariaException("Instituição bancária inválida!");
            }
        }
    }

    private static void validaDataExpedicao(Usuario usuario) {
        if (usuario.getDocumentos() == null) {
            throw new DataExpedicaoInvalida("Não há documentos preenchidos");
        } else {
            usuario.getDocumentos().forEach(documento -> {
                try {
                    documento.isDateValid();
                } catch (DataExpedicaoInvalida e) {
                    throw new DataExpedicaoInvalida(e.getLocalizedMessage());
                }
            });
        }
    }

    private static void validaTipoDocumento(Usuario usuario) {
        usuario.getTipoDocumento().validacao(usuario.getNumeroDeDocumento());
    }

    private static void validaUsuarioSeNull(Usuario usuario) {
        if (usuario == null) {
            throw new UsuarioInvalidoException();
        }
    }

}
