package com.seguradora.api_seguradora.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Cpf é obrigatório")
    @Column(unique = true)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
    private String cpf;
    private String endereco;
    @Column(unique = true)
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "Celular deve estar no formato (XX) 9XXXX-XXXX")
    private String celular;
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculos;
}
