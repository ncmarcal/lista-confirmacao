package com.example.lista_confirmacao.exceptions.handlers;

import com.example.lista_confirmacao.exceptions.SystemErrors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler implements AuthenticationEntryPoint {

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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write("Acesso negado! Você não tem permissão necessária para realizar esta ação.");
    }
}


