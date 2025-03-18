package com.seguradora.api_seguradora.controller;

import com.seguradora.api_seguradora.dto.ClienteDTO;
import com.seguradora.api_seguradora.model.Cliente;
import com.seguradora.api_seguradora.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody Cliente cliente){
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<ClienteDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClienteId(@PathVariable Long id){
        return clienteService.buscarPorId(id)
                .map(cliente -> ResponseEntity.ok(toDTO(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteAtualizado){
        return clienteService.buscarPorId(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(clienteAtualizado.getNome());
                    clienteExistente.setCpf(clienteAtualizado.getCpf());
                    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
                    clienteExistente.setCelular(clienteAtualizado.getCelular());
                    Cliente atualizado = clienteService.salvar(clienteExistente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        if (clienteService.buscarPorId(id).isPresent()){
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ClienteDTO toDTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setEndereco(cliente.getEndereco());
        dto.setCelular(cliente.getCelular());
        return dto;
    }
}
