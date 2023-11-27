package br.com.ada.ifome.usuario;

import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.UsuarioInvalidoException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new UsuarioInvalidoException();
        }
        var isCpfValido = this.validaCpf(usuario.getCpf());
        if (!isCpfValido) {
            throw new CpfInvalidoException();
        }
        return usuarioRepository.save(usuario); // Mockar o usuário repository...
    }

    private boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
}
