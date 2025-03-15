package com.seguradora.api_seguradora.service;

import com.seguradora.api_seguradora.model.Cliente;
import com.seguradora.api_seguradora.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }
}
