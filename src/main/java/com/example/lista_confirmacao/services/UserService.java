package com.example.lista_confirmacao.services;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean checkUserExists(String username) {
        UserDetails user = userRepository.findByUsername(username);
        if (user != null) {
           return user.getUsername().equals(username);
        } else {
            return false;
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
