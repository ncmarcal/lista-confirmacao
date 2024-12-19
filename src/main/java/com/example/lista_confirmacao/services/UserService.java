package com.example.lista_confirmacao.services;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.exceptions.SystemErrors;
import com.example.lista_confirmacao.infra.security.services.TokenService;
import com.example.lista_confirmacao.repositories.UserDetailsRepository;
import com.example.lista_confirmacao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

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

    @Transactional
    public void confirmUserPresence(String username) {
        User userExisting = userRepository.findByUsername(username);
        if (userExisting.isPresence()) {
            LOGGER.error(SystemErrors.ErrorPresenceAlreadyConfirm.MSG_ERROR);
            throw new SystemErrors.ErrorPresenceAlreadyConfirm();
        } else {
            userExisting.setPresence(true);
            userRepository.save(userExisting);
        }
    }
}
