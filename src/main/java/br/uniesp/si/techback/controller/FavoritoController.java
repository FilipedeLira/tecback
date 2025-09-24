package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.service.FavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    // Listar todos os favoritos
    @GetMapping
    public ResponseEntity<List<Favorito>> listar() {
        List<Favorito> favoritos = favoritoService.listar();
        return ResponseEntity.ok(favoritos);
    }

    // Buscar favorito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Favorito> buscarPorId(@PathVariable Long id) {
        return favoritoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo favorito
    @PostMapping
    public ResponseEntity<Favorito> criar(@Valid @RequestBody Favorito favorito) {
        Favorito salvo = favoritoService.salvar(favorito);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    // Atualizar favorito existente
    @PutMapping("/{id}")
    public ResponseEntity<Favorito> atualizar(@PathVariable Long id, @Valid @RequestBody Favorito favorito) {
        return favoritoService.atualizar(id, favorito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar favorito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (favoritoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

