package com.seguradora.api_seguradora.controller;

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
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente){
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable Long id){
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteAtualizado){
        return clienteService.buscarPorId(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(clienteAtualizado.getNome());
                    clienteExistente.setCpf(clienteAtualizado.getCpf());
                    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
                    clienteExistente.setCelular(clienteAtualizado.getCelular());
                    Cliente atualizado = clienteService.salvar(clienteExistente);
                    return ResponseEntity.ok(atualizado);
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
}
