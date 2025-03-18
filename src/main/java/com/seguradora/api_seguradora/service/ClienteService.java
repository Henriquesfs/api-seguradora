package com.seguradora.api_seguradora.service;

import com.seguradora.api_seguradora.dto.ClienteDTO;
import com.seguradora.api_seguradora.model.Cliente;
import com.seguradora.api_seguradora.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente){
        Optional<Cliente> clientePorCpf = clienteRepository.findByCpf(cliente.getCpf());




        if(clientePorCpf.isPresent() && !clientePorCpf.get().getId().equals(cliente.getId())){
            throw new IllegalArgumentException("Erro: Cpf já cadastrado.");
        }

        Optional<Cliente> clientePorCelular = clienteRepository.findByCelular(cliente.getCelular());
        if(clientePorCelular.isPresent() && !clientePorCelular.get().getId().equals(cliente.getId())){
            throw new IllegalArgumentException("Erro: Celular ja cadastrado.");
        }
        return clienteRepository.save(cliente);
    }

    public List<ClienteDTO> listarTodos(){
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getCelular()))
                .collect(Collectors.toList());
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }
}
