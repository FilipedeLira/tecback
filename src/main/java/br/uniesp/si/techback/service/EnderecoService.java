package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> atualizar(Long id, Endereco endereco) {
        return enderecoRepository.findById(id).map(existing -> {
            endereco.setId(existing.getId());
            return enderecoRepository.save(endereco);
        });
    }

    public boolean excluir(Long id) {
        return enderecoRepository.findById(id).map(e -> {
            enderecoRepository.delete(e);
            return true;
        }).orElse(false);
    }
}
