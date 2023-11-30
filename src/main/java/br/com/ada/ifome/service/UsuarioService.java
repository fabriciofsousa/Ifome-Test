package br.com.ada.ifome.service;

import br.com.ada.ifome.enumeration.TipoDeDocumento;
import br.com.ada.ifome.model.Usuario;
import br.com.ada.ifome.exceptions.CnhInvalidoException;
import br.com.ada.ifome.exceptions.CpfInvalidoException;
import br.com.ada.ifome.exceptions.RgInvalidoException;
import br.com.ada.ifome.exceptions.UsuarioInvalidoException;
import br.com.ada.ifome.repository.UsuarioRepository;
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
        if (usuario == null) {
            throw new UsuarioInvalidoException();
        }
//        TipoDeDocumento.validacao(usuario.getTipoDocumento(), usuario.getNumeroDeDocumento());
        usuario.getTipoDocumento().validacao(usuario.getNumeroDeDocumento());
    }

}
