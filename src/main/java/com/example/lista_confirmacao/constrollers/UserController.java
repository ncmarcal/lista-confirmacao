package com.example.lista_confirmacao.constrollers;

import com.example.lista_confirmacao.domain.user.dto.ConfirmVerificationDTO;
import com.example.lista_confirmacao.domain.user.dto.ConfirmationDTO;
import com.example.lista_confirmacao.domain.user.dto.ResponseDTO;
import com.example.lista_confirmacao.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/presence-confirmation")
    public ResponseEntity<ResponseDTO> presenceConfirm(@RequestBody @Valid ConfirmationDTO dto) {
        userService.confirmUserPresence(dto.username());
        return ResponseEntity.ok(new ResponseDTO("Presença confirmada!"));
    }

    @PostMapping("/confirm-verification")
    public ResponseEntity<ConfirmVerificationDTO> confirmVerification(@RequestBody @Valid ConfirmationDTO dto) {
        ConfirmVerificationDTO confirmVerification = userService.confirmVerification(dto.username());
        return ResponseEntity.ok(confirmVerification);
    }

    @PostMapping("/presence-disconfirm")
    public ResponseEntity<ResponseDTO> presenceDisconfirm(@RequestBody @Valid ConfirmationDTO dto) {
        userService.disconfirmPresence(dto.username());
        return ResponseEntity.ok(new ResponseDTO("Presença desconfirmada!"));
    }
}
