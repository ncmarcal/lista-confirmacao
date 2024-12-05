package com.example.lista_confirmacao.domain.user.dto;

import com.example.lista_confirmacao.domain.user.UserRoleEnum;

public record RegisterDTO(String username, String password, UserRoleEnum role, boolean presence) {
}
