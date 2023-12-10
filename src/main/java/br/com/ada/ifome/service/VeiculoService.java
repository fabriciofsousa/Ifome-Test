package br.com.ada.ifome.service;

import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.repository.VeiculoRepository;
import br.com.ada.ifome.util.ValidaVeiculoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;

    public Veiculo salvar(Veiculo veiculo) {
        validacoes(veiculo);
        return veiculoRepository.save(veiculo);
    }

    private void validacoes(Veiculo veiculo) {
        ValidaVeiculoUtil.validacoesVeiculo(veiculo);
    }







}
