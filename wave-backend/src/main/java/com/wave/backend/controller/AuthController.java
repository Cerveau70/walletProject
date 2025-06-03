package com.wave.backend.controller;

import com.wave.backend.dto.Login;
import com.wave.backend.dto.Recharge;
import com.wave.backend.dto.Transfert;
import com.wave.backend.dto.UserDTO;
import com.wave.backend.model.User;
import com.wave.backend.repository.UserRepository;
import com.wave.backend.service.OtpCodeService;
import com.wave.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;
    private final OtpCodeService otpService;

    public AuthController(UserService userService, OtpCodeService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String numero) {
        otpService.sendOtp(numero);
        return ResponseEntity.ok("Un code OTP a été envoyé au numéro " + numero);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        String result = userService.register(userDTO);

        if (result.equals("Utilisateur inscrit avec succès !")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login login) {
        User user = userService.connecter(login);

        if (user != null) {
            return ResponseEntity.ok(user); // Ici plus tard, tu pourras retourner un token JWT
        } else {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
    }
}
