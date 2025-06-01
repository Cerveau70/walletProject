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
@RequestMapping("/users") // Base route, ex: /users/register
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final OtpCodeService otpService;

    public AuthController(UserService userService, UserRepository userRepository, OtpCodeService otpService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.otpService = otpService;
    }

    @PostMapping("/register/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String numero) {
        otpService.sendOtp(numero);
        return ResponseEntity.ok("Un code OTP a été envoyé au numéro " + numero);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        boolean isValidOtp = otpService.verifyOtp(userDTO.getNumero(), userDTO.getOtpCode());

        if (!isValidOtp) {
            return ResponseEntity.badRequest().body("OTP invalide ou expiré.");
        }

        if (userRepository.existsByNumero(userDTO.getNumero())) {
            return ResponseEntity.badRequest().body("Ce numéro est déjà enregistré.");
        }

        User user = new User();
        user.setNumero(userDTO.getNumero());
        user.setNom(userDTO.getNom());
        user.setMdp(userDTO.getMotDePasse());

        userRepository.save(user);
        otpService.clearOtp(user.getNumero());

        return ResponseEntity.ok("Utilisateur inscrit avec succès !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login login) {
        User user = userService.connecter(login);

        if (user != null) {
            return ResponseEntity.ok(user); // Pour le moment, on retourne l'utilisateur
        } else {
            return ResponseEntity
                    .status(401)
                    .body("Identifiants invalides");
        }
    }

}
