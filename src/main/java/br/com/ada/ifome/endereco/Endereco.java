package br.com.ada.ifome.endereco;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue
    private Long id;
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
}
