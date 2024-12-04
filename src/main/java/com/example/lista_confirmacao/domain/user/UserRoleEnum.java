package com.example.lista_confirmacao.domain.user;

public enum UserRoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
