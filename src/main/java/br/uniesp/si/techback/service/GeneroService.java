package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    public Optional<Genero> buscarPorId(Long id) {
        return generoRepository.findById(id);
    }

    public Genero salvar(Genero genero) {
        return generoRepository.save(genero);
    }

    public Optional<Genero> atualizar(Long id, Genero genero) {
        return generoRepository.findById(id).map(existing -> {
            genero.setId(existing.getId());
            return generoRepository.save(genero);
        });
    }

    public boolean excluir(Long id) {
        return generoRepository.findById(id).map(g -> {
            generoRepository.delete(g);
            return true;
        }).orElse(false);
    }
}
