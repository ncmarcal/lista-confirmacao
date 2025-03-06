package com.example.lista_confirmacao.services;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.domain.user.dto.ConfirmVerificationDTO;
import com.example.lista_confirmacao.exceptions.SystemErrors;
import com.example.lista_confirmacao.repositories.UserDetailsRepository;
import com.example.lista_confirmacao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void confirmUserPresence(String username) {
        User userExisting = userRepository.findByUsername(username);
        if (confirmVerification(userExisting.getUsername()).presence()) {
            LOGGER.error(SystemErrors.ErrorPresenceAlreadyConfirm.MSG_ERROR);
            throw new SystemErrors.ErrorPresenceAlreadyConfirm();
        } else {
            userExisting.setPresence(true);
            userRepository.save(userExisting);
        }
    }

    public ConfirmVerificationDTO confirmVerification(String username) {
        User userExisting = userRepository.findByUsername(username);
        return new ConfirmVerificationDTO(userExisting.isPresence());
    }
}
