package com.example.lista_confirmacao.exceptions;

public class SystemErrors {

    public static final class ErrorUserAlreadyExists extends RuntimeException {
        public static final String MSG_ERROR = "Usuário já cadastrado!";
        public ErrorUserAlreadyExists() {
            super(MSG_ERROR);
        }
    }

    public static final class ErrorTokenGeneration extends RuntimeException {
        public static final String MSG_ERROR = "Falha ao gerar token!";
        public ErrorTokenGeneration(Throwable throwable) {
            super(MSG_ERROR, throwable);
        }
    }

    public static final class ErrorPresenceAlreadyConfirm extends RuntimeException {
        public static final String MSG_ERROR = "Sua presença já está confirmada!";
        public ErrorPresenceAlreadyConfirm() {
            super(MSG_ERROR);
        }
    }

}

