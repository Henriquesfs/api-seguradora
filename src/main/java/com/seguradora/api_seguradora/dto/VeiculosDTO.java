package com.seguradora.api_seguradora.dto;

import lombok.Data;

@Data
public class VeiculosDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private ClienteDTO cliente;


    public VeiculosDTO() {

    }

    public VeiculosDTO(String placa, String modelo, String marca, ClienteDTO cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cliente = cliente;
    }
}
