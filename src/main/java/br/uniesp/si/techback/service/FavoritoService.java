package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public List<Favorito> listar() {
        return favoritoRepository.findAll();
    }

    public Optional<Favorito> buscarPorId(Long id) {
        return favoritoRepository.findById(id);
    }

    public Favorito salvar(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public Optional<Favorito> atualizar(Long id, Favorito favorito) {
        return favoritoRepository.findById(id).map(existing -> {
            favorito.setId(existing.getId());
            return favoritoRepository.save(favorito);
        });
    }

    public boolean excluir(Long id) {
        return favoritoRepository.findById(id).map(f -> {
            favoritoRepository.delete(f);
            return true;
        }).orElse(false);
    }
}
