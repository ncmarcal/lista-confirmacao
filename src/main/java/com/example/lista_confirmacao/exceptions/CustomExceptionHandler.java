package com.example.lista_confirmacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(SystemErrors.ErrorUserAlreadyExists.class)
    public ResponseEntity<String> handleErrorUserAlreadyExists(SystemErrors.ErrorUserAlreadyExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SystemErrors.ErrorTokenGeneration.class)
    public ResponseEntity<String> handleErrorTokenGeneration(SystemErrors.ErrorTokenGeneration ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemErrors.ErrorPresenceAlreadyConfirm.class)
    public ResponseEntity<String> handleErrorPresenceAlreadyConfirm(SystemErrors.ErrorPresenceAlreadyConfirm ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    //TODO: fazer com que essa mensagem seja lançada nos 403 que existir
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Acesso negado: você não tem permissão para acessar este recurso.", HttpStatus.FORBIDDEN);
    }
}


