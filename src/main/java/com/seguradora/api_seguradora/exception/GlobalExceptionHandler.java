package com.seguradora.api_seguradora.exception;

import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String mensagem = "Erro: Verifique os campos únicos (cpf, celular, placa)";
        if(e.getMessage().contains("cpf")) {
            mensagem = "Cpf inválido.";
        } else if(e.getMessage().contains("celular")) {
            mensagem = "Erro, celular já cadastrado.";
        } else if(e.getMessage().contains("placa")) {
            mensagem = "Erro, placa já cadastrada.";
        }
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
