package br.com.ada.ifome.exceptions.usuario;

public class ContaBancariaException extends RuntimeException {
    public ContaBancariaException(String erro) {
        super(erro);
    }
}
