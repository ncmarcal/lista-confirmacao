package com.example.lista_confirmacao.constrollers;

import com.example.lista_confirmacao.domain.user.dto.ConfirmationDTO;
import com.example.lista_confirmacao.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/presence-confirmation")
    public ResponseEntity<String> presenceConfirm(@RequestBody @Valid ConfirmationDTO dto) {
        userService.confirmUserPresence(dto.username());
        return ResponseEntity.ok("Presença confirmada!");
    }
}
