package br.com.ada.ifome.usuario;

import br.com.ada.ifome.endereco.Endereco;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {

    @Id
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

}
