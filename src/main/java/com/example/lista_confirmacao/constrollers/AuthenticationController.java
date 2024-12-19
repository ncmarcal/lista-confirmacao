package com.example.lista_confirmacao.constrollers;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.domain.user.dto.AuthenticatonDTO;
import com.example.lista_confirmacao.domain.user.dto.LoginResponseDTO;
import com.example.lista_confirmacao.domain.user.dto.RegisterDTO;
import com.example.lista_confirmacao.exceptions.SystemErrors;
import com.example.lista_confirmacao.infra.security.services.TokenService;
import com.example.lista_confirmacao.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticatonDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
        if (userService.checkUserExists(dto.username())) {
            LOGGER.error(SystemErrors.ErrorUserAlreadyExists.MSG_ERROR);
            throw new SystemErrors.ErrorUserAlreadyExists();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.username(), encryptedPassword, dto.role(), dto.presence());
        userService.saveUserDetails(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
