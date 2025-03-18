package com.seguradora.api_seguradora.service;

import com.seguradora.api_seguradora.model.Veiculo;
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

    public Veiculo salvar(Veiculo veiculo) {
        Optional<Veiculo> veiculoPorPlaca = veiculosRepository.findByPlaca(veiculo.getPlaca());

        if(veiculo.getCliente() == null || veiculo.getCliente().getId() == null){
            throw new IllegalArgumentException("Erro: Cliente é obrigatório.");
        }

        if (veiculoPorPlaca.isPresent() && !veiculoPorPlaca.get().getPlaca().equals(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Erro: Placa já cadastrada.");
        }
        return veiculosRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos(){
        return veiculosRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id){
        return veiculosRepository.findById(id);
    }

    public void deletar(Long id){
        veiculosRepository.deleteById(id);
    }
}
