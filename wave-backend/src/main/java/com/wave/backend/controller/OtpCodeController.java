package com.wave.backend.controller;

import com.wave.backend.service.OtpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpCodeController {

    @Autowired
    private OtpCodeService otpService;

    // Endpoint pour envoyer un OTP
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String numero) {
        String otp = otpService.sendOtp(numero);
        return ResponseEntity.ok("OTP envoyé au numéro : " + numero);
    }

    // Endpoint pour vérifier un OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String numero, @RequestParam String code) {
        boolean isValid = otpService.verifyOtp(numero, code);

        if (isValid) {
            otpService.clearOtp(numero);
            return ResponseEntity.ok("OTP vérifié avec succès !");
        } else {
            return ResponseEntity.badRequest().body("Code invalide ou expiré !");
        }
    }
}
