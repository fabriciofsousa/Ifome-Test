package br.com.ada.ifome.controller;

import br.com.ada.ifome.dto.VeiculoDTO;
import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> salvar(@RequestBody VeiculoDTO veiculo) {
        return ResponseEntity.status(201).body(veiculoService.salvar(new Veiculo().Veiculo(veiculo)));
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarTodos() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody VeiculoDTO veiculo) {
        return ResponseEntity.ok(veiculoService.atualizar(id, new Veiculo().Veiculo(veiculo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        veiculoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
