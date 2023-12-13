package br.com.ada.ifome.dto;

import br.com.ada.ifome.model.Veiculo;
import lombok.*;

import javax.persistence.Column;
import java.util.Calendar;

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VeiculoDTO {
    private String renavam;
    private Calendar dataModelo;
    private String placa;

    public VeiculoDTO VeiculoDTO(Veiculo veiculo) {
        this.renavam = veiculo.getRenavam();
        this.dataModelo = veiculo.getDataModelo();
        this.placa = veiculo.getPlaca();
        return this;
    }
}
