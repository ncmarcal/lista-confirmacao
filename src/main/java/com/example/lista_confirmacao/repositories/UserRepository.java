package com.example.lista_confirmacao.repositories;

import com.example.lista_confirmacao.domain.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @NonNull
    List<User> findAll();
}
