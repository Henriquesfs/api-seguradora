package com.seguradora.api_seguradora.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_veiculos")
public class Veiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "marca é obrigatório")
    private String marca;
    @NotBlank(message = "modelo é obrigatório")
    private String modelo;
    @NotBlank(message = "placa é obrigatório")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonManagedReference
    private Cliente cliente;

}
