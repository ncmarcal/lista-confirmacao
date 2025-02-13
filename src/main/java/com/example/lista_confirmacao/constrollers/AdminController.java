package com.example.lista_confirmacao.constrollers;

import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.domain.user.dto.AllUsersDTO;
import com.example.lista_confirmacao.domain.user.dto.RegisterDTO;
import com.example.lista_confirmacao.domain.user.dto.ResponseDTO;
import com.example.lista_confirmacao.exceptions.SystemErrors;
import com.example.lista_confirmacao.services.AdminService;
import com.example.lista_confirmacao.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @GetMapping("/list-all-users")
    public ResponseEntity<List<AllUsersDTO>> listAllUsers() {
        List<AllUsersDTO> allUsers = adminService.listAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterDTO dto) {
        if (adminService.checkUserExists(dto.username())) {
            LOGGER.error(SystemErrors.ErrorUserAlreadyExists.MSG_ERROR);
            throw new SystemErrors.ErrorUserAlreadyExists();
        }
        validateRequisitionRegister(dto);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.username(), encryptedPassword, dto.role(), dto.presence());
        adminService.saveUserDetails(user);
        return ResponseEntity.ok(new ResponseDTO("Usuário cadastrado com sucesso!"));
    }

    private void validateRequisitionRegister(RegisterDTO dto) {
        if (dto.username().isEmpty()) {
            throw new SystemErrors.ErroUserNameInvalid();
        }

        if (dto.password().isEmpty()) {
            throw new SystemErrors.ErroPasswordInvalid();
        }
    }

}
