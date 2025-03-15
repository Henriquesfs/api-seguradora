package com.seguradora.api_seguradora.service;

import com.seguradora.api_seguradora.model.Veiculos;
import com.seguradora.api_seguradora.repository.VeiculosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculosService {
    private final VeiculosRepository veiculosRepository;
    public VeiculosService(VeiculosRepository veiculosRepository) {
        this.veiculosRepository = veiculosRepository;
    }

    public Veiculos salvar(Veiculos veiculos) {
        return veiculosRepository.save(veiculos);
    }

    public List<Veiculos> listarTodos(){
        return veiculosRepository.findAll();
    }

    public Optional<Veiculos> buscarPorId(Long id){
        return veiculosRepository.findById(id);
    }

    public void deletar(Long id){
        veiculosRepository.deleteById(id);
    }
}
