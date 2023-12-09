package br.com.ada.ifome.enumeration;

public enum InstituicaoBancaria {
    BANCO_DO_BRASIL(001),
    CAIXA_ECONOMICA(104),
    ITAU(341),
    BRADESCO(237),
    SANTANDER(033);

    private final int codigo;

    InstituicaoBancaria(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    // Validador básico
    public static boolean validarCodigo(int codigo) {
        for (InstituicaoBancaria instituicao : InstituicaoBancaria.values()) {
            if (instituicao.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

}
