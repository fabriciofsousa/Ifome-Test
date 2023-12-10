package br.com.ada.ifome.enumeration;

public enum TipoConta {
    CONTA_CORRENTE(1),
    CONTA_POUPANCA(2),
    CONTA_SALARIO(3),
    CONTA_UNIVERSITARIA(4),
    CONTA_DIGITAL(5),
    CONTA_CONJUNTA(6),
    CONTA_EMPRESARIAL(7),
    CONTA_INVESTIMENTO(8);

    private final int codigo;

    TipoConta(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    // Validador básico
    public static boolean validarCodigo(int codigo) {
        for (TipoConta instituicao : TipoConta.values()) {
            if (instituicao.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    }
