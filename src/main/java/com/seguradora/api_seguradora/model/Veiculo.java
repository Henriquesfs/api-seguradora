package com.seguradora.api_seguradora.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "marca é obrigatório")
    private String marca;
    @NotBlank(message = "modelo é obrigatório")
    private String modelo;
    @NotBlank(message = "placa é obrigatório")
    @Column(unique = true)
    @Pattern(regexp = "[A-Z]{3}-\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}", message = "Placa deve estar no formato XXX-1234 ou XXX1X23")
    private String placa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

}
