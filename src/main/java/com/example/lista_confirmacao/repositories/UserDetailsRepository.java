package com.example.lista_confirmacao.repositories;

import com.example.lista_confirmacao.domain.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User, Integer> {

    UserDetails findByUsername(String username);

    @NonNull
    Optional<User> findById(@NonNull  Integer id);

}
