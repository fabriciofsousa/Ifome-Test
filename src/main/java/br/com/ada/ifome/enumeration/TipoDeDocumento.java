package br.com.ada.ifome.enumeration;

import br.com.ada.ifome.exceptions.usuario.CnhInvalidoException;
import br.com.ada.ifome.exceptions.usuario.CpfInvalidoException;
import br.com.ada.ifome.exceptions.usuario.RgInvalidoException;
import lombok.Getter;

@Getter
public enum TipoDeDocumento {

    CPF("cpf", 11L, new CpfInvalidoException()),
    RG("rg", 7L, new RgInvalidoException()),
    CNH("cnh", 11L, new CnhInvalidoException());

    private String descricao;
    private Long qttCaracteres;
    private RuntimeException exception;


    TipoDeDocumento(String descricao, long qttCaracteres, RuntimeException exception) {
        this.qttCaracteres = qttCaracteres;
        this.descricao = descricao;
        this.exception = exception;
    }

    public void validacao(String numeroDeDocumento){
        numeroDeDocumento = numeroDeDocumento.replaceAll("[^0-9]", "");
        if (numeroDeDocumento.length() != this.getQttCaracteres()){
            throw this.exception;
        }


    }
}
