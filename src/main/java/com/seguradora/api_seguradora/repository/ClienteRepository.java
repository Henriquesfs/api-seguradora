package com.seguradora.api_seguradora.repository;

import com.seguradora.api_seguradora.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
