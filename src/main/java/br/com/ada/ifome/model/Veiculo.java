package br.com.ada.ifome.model;

import br.com.ada.ifome.dto.VeiculoDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String renavam;
    private Calendar dataModelo;
    private String placa;


    public Veiculo Veiculo(VeiculoDTO veiculoDTO) {
        this.renavam = veiculoDTO.getRenavam();
        this.dataModelo = veiculoDTO.getDataModelo();
        this.placa = veiculoDTO.getPlaca();
        return this;
    }
}
