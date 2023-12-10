package br.com.ada.ifome.util;

import br.com.ada.ifome.exceptions.veiculo.VeiculoDataModeloInvalidoException;
import br.com.ada.ifome.exceptions.veiculo.VeiculoInvalidoException;
import br.com.ada.ifome.exceptions.veiculo.VeiculoPlacaVeiculoInvalidoException;
import br.com.ada.ifome.exceptions.veiculo.VeiculoRenavamInvalidoException;
import br.com.ada.ifome.model.Veiculo;

import java.util.Calendar;
import java.util.regex.Pattern;

public class ValidaVeiculoUtil {

    protected static final String PLACA_VEICULO_PATTERN = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}";

    public static void validacoesVeiculo(Veiculo veiculo) {
        validaVeiculo(veiculo);
    }

    private static void validaVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new VeiculoInvalidoException();
        }

        if (veiculo.getDataModelo() == null){
            throw new VeiculoDataModeloInvalidoException();
        }

        if (veiculo.getPlaca() == null){
            throw new VeiculoPlacaVeiculoInvalidoException();
        }

        if (veiculo.getRenavam() == null){
            throw new VeiculoRenavamInvalidoException();
        }

        Calendar ano2010 = Calendar.getInstance();
        ano2010.set(Calendar.YEAR, 2010);
        ano2010.set(Calendar.MONTH, Calendar.JANUARY);
        ano2010.set(Calendar.DAY_OF_MONTH, 1);

        // No metodo compareTo, da classe Calendar:
        // 0: Data são iguais
        // menor que 0: Data é anterior
        // maior que 0: Data é posterior
        if (veiculo.getDataModelo().compareTo(ano2010) < 0) {
            throw new VeiculoDataModeloInvalidoException();
        }

        // Placa é válida se estiver no formato
        if (!validaPlacaVeiculo(veiculo.getPlaca())){
            throw new VeiculoPlacaVeiculoInvalidoException();
        }

        if (!validarRenavam(veiculo.getRenavam())){
            throw new VeiculoRenavamInvalidoException();
        }
    }

    private static boolean validaPlacaVeiculo(String placa) {
        return Pattern.matches(PLACA_VEICULO_PATTERN, placa);
    }

    // Implementacao copiada originalmente de https://victorjabur.com/2010/05/28/renavam_veiculos_java/
    // em 04/12/2023
    private static boolean validarRenavam(String renavam) {
        // Pegando como exemplo o renavam = 639884962

        // Completa com zeros a esquerda se for no padrao antigo de 9 digitos
        // renavam = 00639884962
        if (renavam.matches("^([0-9]{9})$")) {
            renavam = "00" + renavam;
        }

        // Valida se possui 11 digitos pos formatacao
        if (!renavam.matches("[0-9]{11}")) {
            return false;
        }

        // Remove o digito (11 posicao)
        // renavamSemDigito = 0063988496
        String renavamSemDigito = renavam.substring(0, 10);

        // Inverte os caracteres (reverso)
        // renavamReversoSemDigito = 69488936
        String renavamReversoSemDigito = new StringBuffer(renavamSemDigito).reverse().toString();

        int soma = 0;

        // Multiplica as strings reversas do renavam pelos numeros multiplicadores
        // para apenas os primeiros 8 digitos de um total de 10
        // Exemplo: renavam reverso sem digito = 69488936
        // 6, 9, 4, 8, 8, 9, 3, 6
        // * * * * * * * *
        // 2, 3, 4, 5, 6, 7, 8, 9 (numeros multiplicadores – sempre os mesmos [fixo])
        // 12 + 27 + 16 + 40 + 48 + 63 + 24 + 54
        // soma = 284
        for (int i = 0; i < 8; i++) {
            Integer algarismo = Integer.parseInt(renavamReversoSemDigito.substring(i, i + 1));
            Integer multiplicador = i + 2;
            soma += algarismo * multiplicador;
        }

        // Multiplica os dois ultimos digitos e soma
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(8)) * 2;
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(9)) * 3;

        // mod11 = 284 % 11 = 9 (resto da divisao por 11)
        int mod11 = soma % 11;

        // Faz-se a conta 11 (valor fixo) – mod11 = 11 – 9 = 2
        int ultimoDigitoCalculado = 11 - mod11;

        // ultimoDigito = Caso o valor calculado anteriormente seja 10 ou 11, transformo ele em 0
        // caso contrario, eh o proprio numero
        ultimoDigitoCalculado = (ultimoDigitoCalculado >= 10 ? 0 : ultimoDigitoCalculado);

        // Pego o ultimo digito do renavam original (para confrontar com o calculado)
        int digitoRealInformado = Integer.parseInt(renavam.substring(renavam.length() - 1));

        // Comparo os digitos calculado e informado
        return ultimoDigitoCalculado == digitoRealInformado;
    }
}
