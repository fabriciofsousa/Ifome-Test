package br.com.ada.ifome.model;

import br.com.ada.ifome.enumeration.InstituicaoBancaria;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="contaBancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroAgencia;
    private Integer numeroConta;
    private InstituicaoBancaria tipoConta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
