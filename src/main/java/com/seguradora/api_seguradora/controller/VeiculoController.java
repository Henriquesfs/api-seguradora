package com.seguradora.api_seguradora.controller;

import com.seguradora.api_seguradora.dto.ClienteDTO;
import com.seguradora.api_seguradora.dto.VeiculoDTO;
import com.seguradora.api_seguradora.model.Veiculo;
import com.seguradora.api_seguradora.service.VeiculosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculosService veiculosService;

    public VeiculoController(VeiculosService veiculosService) {
        this.veiculosService = veiculosService;
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> adicionarVeiculo(@Valid @RequestBody Veiculo veiculo) {
        Veiculo salvo = veiculosService.salvar(veiculo);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
        List<Veiculo> veiculos = veiculosService.listarTodos();
        return ResponseEntity.ok(veiculos.stream().map(this::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id) {
        return veiculosService.buscarPorId(id)
                .map(veiculo -> ResponseEntity.ok(toDTO(veiculo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
        return veiculosService.buscarPorId(id)
                .map(veiculoExistente -> {
                    veiculoExistente.setCliente(veiculo.getCliente());
                    veiculoExistente.setMarca(veiculo.getMarca());
                    veiculoExistente.setModelo(veiculo.getModelo());
                    veiculoExistente.setPlaca(veiculo.getPlaca());
                    Veiculo atualizado = veiculosService.salvar(veiculoExistente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        if (veiculosService.buscarPorId(id).isPresent()) {
            veiculosService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private VeiculoDTO toDTO(Veiculo veiculo) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setMarca(veiculo.getMarca());
        dto.setModelo(veiculo.getModelo());
        dto.setPlaca(veiculo.getPlaca());
        if (veiculo.getCliente() != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(veiculo.getCliente().getId());
            clienteDTO.setNome(veiculo.getCliente().getNome());
            clienteDTO.setCpf(veiculo.getCliente().getCpf());
            clienteDTO.setEndereco(veiculo.getCliente().getEndereco());
            clienteDTO.setCelular(veiculo.getCliente().getCelular());
            dto.setCliente(clienteDTO);
        }
        return dto;
    }
}
