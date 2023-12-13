package br.com.ada.ifome.exceptions.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioNaoEncontradoException() {
        super();
    }
}
