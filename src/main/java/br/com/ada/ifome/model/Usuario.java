package br.com.ada.ifome.model;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.documents.Documento;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="usuario")
public class Usuario {

    @Id
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String numeroDeDocumento;

    @Column(unique = true, nullable = false)
    private TipoDeDocumento tipoDocumento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Documento> documentos;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<ContaBancaria> contasBancarias;

}
