package br.com.ada.ifome.endereco;

import br.com.ada.ifome.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
