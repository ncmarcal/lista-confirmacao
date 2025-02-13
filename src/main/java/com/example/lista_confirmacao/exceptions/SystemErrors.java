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

    public static final class ErrorForbiddenConsultUsers extends RuntimeException {
        public static final String MSG_ERROR = "Você não tem permissão para consultar a lista de usuários.";
        public ErrorForbiddenConsultUsers() {
            super(MSG_ERROR);
        }
    }

    public static final class ErroUserNameInvalid extends RuntimeException {
        public static final String MSG_ERROR = "Nome de usuário inválido!";
        public ErroUserNameInvalid() {
            super(MSG_ERROR);
        }
    }

    public static final class ErroPasswordInvalid extends RuntimeException {
        public static final String MSG_ERROR = "Senha inválida!";
        public ErroPasswordInvalid() {
            super(MSG_ERROR);
        }
    }

    public static final class ErroUserNotExists extends RuntimeException {
        public static final String MSG_ERROR = "Usuário inexistente!";
        public ErroUserNotExists() {
            super(MSG_ERROR);
        }
    }

    public static final class ErroIdInvalido extends RuntimeException {
        public static final String MSG_ERROR = "ID de usuário inválido!";
        public ErroIdInvalido() {
            super(MSG_ERROR);
        }
    }

}

