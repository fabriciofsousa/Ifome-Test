package br.com.ada.ifome.exceptions.veiculo;

public class VeiculoInvalidoException extends RuntimeException {

    public VeiculoInvalidoException(String message) {
        super(message);
    }

    public VeiculoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeiculoInvalidoException() {
        super();
    }
}
