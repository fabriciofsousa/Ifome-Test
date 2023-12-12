package br.com.ada.ifome.controller;

import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {
    private final VeiculoService veiculousuarioService;

    @PostMapping
    public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo) {
        return ResponseEntity.status(201).body(veiculousuarioService.salvar(veiculo));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
//        usuario.setId(id);
//        return ResponseEntity.ok(veiculousuarioService.salvar(usuario));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Usuario> remover(@PathVariable Long id) {
//        return ResponseEntity.ok(veiculousuarioService.remover(id));
//    }
}
