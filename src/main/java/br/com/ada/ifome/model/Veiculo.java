package br.com.ada.ifome.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Data
public class Veiculo {
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String renavam;
    private Calendar dataModelo;
    private String placa;
}
