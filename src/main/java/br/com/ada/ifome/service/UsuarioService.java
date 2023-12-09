package br.com.ada.ifome.service;

import br.com.ada.ifome.exceptions.DataExpedicaoInvalida;
import br.com.ada.ifome.exceptions.UsuarioInvalidoException;
import br.com.ada.ifome.model.Usuario;
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
        validaUsuario(usuario);
        validaTipoDocumento(usuario);
        validaDataExpedicao(usuario);
    }

    private static void validaDataExpedicao(Usuario usuario) {
        if (usuario.getDocumentos() == null) {
            throw new DataExpedicaoInvalida("Não há documentos preenchidos");
        } else {
            usuario.getDocumentos().forEach(documento -> {
                try {
                    documento.isDateValid();
                } catch (DataExpedicaoInvalida e) {
                    throw new DataExpedicaoInvalida(e.getLocalizedMessage());
                }
            });
        }
    }

    private static void validaTipoDocumento(Usuario usuario) {
        usuario.getTipoDocumento().validacao(usuario.getNumeroDeDocumento());
    }

    private static void validaUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new UsuarioInvalidoException();
        }
    }

}
