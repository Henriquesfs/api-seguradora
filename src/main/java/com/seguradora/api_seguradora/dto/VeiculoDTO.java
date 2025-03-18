package com.seguradora.api_seguradora.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private ClienteDTO cliente;


    public VeiculoDTO() {

    }

    public VeiculoDTO(String placa, String modelo, String marca, ClienteDTO cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cliente = cliente;
    }
}
