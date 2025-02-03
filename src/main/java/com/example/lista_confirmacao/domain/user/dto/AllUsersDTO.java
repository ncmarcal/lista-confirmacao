package com.example.lista_confirmacao.domain.user.dto;

import com.example.lista_confirmacao.domain.user.UserRoleEnum;

public record AllUsersDTO(Integer id, String username, UserRoleEnum role, boolean presence) {
}
