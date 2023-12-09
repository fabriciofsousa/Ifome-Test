package br.com.ada.ifome.model.documents;

import br.com.ada.ifome.exceptions.DataExpedicaoInvalida;
import br.com.ada.ifome.model.Usuario;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public abstract class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private LocalDate dataExpedicao;

    private String nome;
    private String orgaoExpedidor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void isDateValid() throws DataExpedicaoInvalida {
        LocalDate currentDate = LocalDate.now();
        if (dataExpedicao == null || currentDate.isAfter(dataExpedicao)){
            throw new DataExpedicaoInvalida("Data não informada ou expirada!");
        }
    }

}