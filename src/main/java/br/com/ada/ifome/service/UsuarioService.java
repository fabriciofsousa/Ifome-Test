package br.com.ada.ifome.service;

import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.repository.UsuarioRepository;
import br.com.ada.ifome.util.ValidaUsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        validacoes(usuario);
        return usuarioRepository.save(usuario); // Mockar o usuário repository...
    }

    private void validacoes(Usuario usuario) {
        ValidaUsuarioUtil.validaUsuario(usuario);
    }

}
