package com.example.lista_confirmacao.repositories;

import com.example.lista_confirmacao.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsRepository extends JpaRepository<User, Integer> {

    UserDetails findByUsername(String username);

}
