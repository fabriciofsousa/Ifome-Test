package br.com.ada.ifome.model;

import br.com.ada.ifome.enumeration.InstituicaoBancaria;
import br.com.ada.ifome.enumeration.TipoConta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name="contaBancaria")
@Getter
@Setter
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroAgencia;
    private Integer numeroConta;
    private InstituicaoBancaria instituicaoBancaria;
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
