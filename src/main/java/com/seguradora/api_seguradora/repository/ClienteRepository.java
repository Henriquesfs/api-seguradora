package com.seguradora.api_seguradora.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.seguradora.api_seguradora.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByCelular(String celular);
}
