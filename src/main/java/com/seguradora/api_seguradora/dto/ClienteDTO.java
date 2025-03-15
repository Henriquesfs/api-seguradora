package com.seguradora.api_seguradora.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String celular;

    public ClienteDTO() {

    }
    public ClienteDTO(String celular, String endereco, String cpf, String nome) {
        this.celular = celular;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nome = nome;
    }
}
