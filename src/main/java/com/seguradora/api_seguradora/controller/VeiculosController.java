package com.seguradora.api_seguradora.controller;

import com.seguradora.api_seguradora.dto.ClienteDTO;
import com.seguradora.api_seguradora.dto.VeiculosDTO;
import com.seguradora.api_seguradora.model.Cliente;
import com.seguradora.api_seguradora.model.Veiculos;
import com.seguradora.api_seguradora.service.VeiculosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculosController {
    private final VeiculosService veiculosService;

    public VeiculosController(VeiculosService veiculosService) {
        this.veiculosService = veiculosService;
    }

    @PostMapping
    public ResponseEntity<Veiculos> adicionarVeiculo(@Valid @RequestBody Veiculos veiculo){
        Veiculos salvo = veiculosService.salvar(veiculo);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculos>> listarVeiculos(){
        List<Veiculos> veiculo = veiculosService.listarTodos();
        return ResponseEntity.ok(veiculo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<VeiculosDTO>> listarVeiculos(@PathVariable Long id) {
        List<Veiculos> veiculos = veiculosService.listarTodos();
        List<VeiculosDTO> veiculosDTOs = veiculos.stream().map(veiculo -> {
            VeiculosDTO dto = new VeiculosDTO();
            dto.setId(veiculo.getId());
            dto.setMarca(veiculo.getMarca());
            dto.setModelo(veiculo.getModelo());
            dto.setPlaca(veiculo.getPlaca());


            Cliente cliente = veiculo.getCliente();
            if (cliente != null){
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setId((cliente.getId()));
                clienteDTO.setNome(cliente.getNome());
                clienteDTO.setCpf(cliente.getCpf());
                clienteDTO.setEndereco(cliente.getEndereco());
                clienteDTO.setCelular(cliente.getCelular());
                dto.setCliente(clienteDTO);
            }

            return dto;
        }).toList();
        return ResponseEntity.ok(veiculosDTOs);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Veiculos> atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculos veiculo){
        return veiculosService.buscarPorId(id)
                .map(veiculoExistente -> {
                    veiculoExistente.setCliente(veiculo.getCliente());
                    veiculoExistente.setMarca(veiculo.getMarca());
                    veiculoExistente.setModelo(veiculo.getModelo());
                    veiculoExistente.setPlaca(veiculo.getPlaca());
                    Veiculos atualizado = veiculosService.salvar(veiculoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id){
        if(veiculosService.buscarPorId(id).isPresent()){
            veiculosService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

