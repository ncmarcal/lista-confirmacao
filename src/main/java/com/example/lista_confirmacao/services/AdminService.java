package com.example.lista_confirmacao.services;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.domain.user.dto.AllUsersDTO;
import com.example.lista_confirmacao.repositories.UserDetailsRepository;
import com.example.lista_confirmacao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public List<AllUsersDTO> listAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(obj -> new AllUsersDTO(obj.getId(), obj.getUsername(), obj.getRole(), obj.isPresence())).collect(Collectors.toList());
    }

    public boolean checkUserExists(String username) {
        UserDetails user = userDetailsRepository.findByUsername(username);
        if (user != null) {
            return user.getUsername().equals(username);
        } else {
            return false;
        }
    }

    @Transactional
    public void saveUserDetails(User user) {
        userDetailsRepository.save(user);
    }
}
