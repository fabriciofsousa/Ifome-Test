package br.com.ada.ifome.exceptions.veiculo;

public class VeiculoNaoEncontradoException extends RuntimeException {

    public VeiculoNaoEncontradoException(String message) {
        super(message);
    }

    public VeiculoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeiculoNaoEncontradoException() {
        super();
    }
}
