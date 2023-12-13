package br.com.ada.ifome.service;

import br.com.ada.ifome.exceptions.veiculo.VeiculoInvalidoException;
import br.com.ada.ifome.exceptions.veiculo.VeiculoNaoEncontradoException;
import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.repository.VeiculoRepository;
import br.com.ada.ifome.util.ValidaVeiculoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo salvar(Veiculo veiculo) {
        try {
            validacoes(veiculo);
            return veiculoRepository.save(veiculo);
        } catch (Exception erro) {
            throw new VeiculoInvalidoException(erro.getMessage());
        }
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo atualizar(Long id, Veiculo veiculo) {
        if (!veiculoRepository.existsById(id)) {
            throw new VeiculoNaoEncontradoException("Veículo não encontrado");
        }
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    public void remover(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new VeiculoInvalidoException("Veículo não encontrado");
        }
        veiculoRepository.deleteById(id);
    }

    private void validacoes(Veiculo veiculo) {
        ValidaVeiculoUtil.validacoesVeiculo(veiculo);
    }
}
