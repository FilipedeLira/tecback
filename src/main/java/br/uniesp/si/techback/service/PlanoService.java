package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;

    public List<Plano> listar() {
        return planoRepository.findAll();
    }

    public Optional<Plano> buscarPorId(Long id) {
        return planoRepository.findById(id);
    }

    public Plano salvar(Plano plano) {
        return planoRepository.save(plano);
    }

    public Optional<Plano> atualizar(Long id, Plano plano) {
        return planoRepository.findById(id).map(existing -> {
            plano.setId(existing.getId());
            return planoRepository.save(plano);
        });
    }

    public boolean excluir(Long id) {
        return planoRepository.findById(id).map(p -> {
            planoRepository.delete(p);
            return true;
        }).orElse(false);
    }
}

