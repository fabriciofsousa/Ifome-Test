package br.com.ada.ifome.model;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {

    @Id
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String numeroDeDocumento;

    @Column(unique = true, nullable = false)
    private TipoDeDocumento tipoDocumento;

    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

}
