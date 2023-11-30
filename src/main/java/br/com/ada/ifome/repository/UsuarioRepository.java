package br.com.ada.ifome.repository;

import br.com.ada.ifome.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
