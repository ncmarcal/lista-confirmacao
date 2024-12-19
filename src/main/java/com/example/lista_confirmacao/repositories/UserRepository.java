package com.example.lista_confirmacao.repositories;

import com.example.lista_confirmacao.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
