package com.wave.backend.service;

import com.wave.backend.model.OtpCode;
import com.wave.backend.repository.OtpCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class OtpCodeService{

    @Autowired
    private OtpCodeRepository otpCodeRepository;

    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5 minutes

    // Générer un OTP aléatoire à 6 chiffres
    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    // Envoyer l’OTP à l’utilisateur (via console ici pour test)
    public String sendOtp(String numero) {
        otpCodeRepository.findByNumero(numero).ifPresent(otpCodeRepository::delete);

        String otp = generateOtp();
        OtpCode otpCode = new OtpCode();
        otpCode.setNumero(numero);
        otpCode.setCode(otp);
        otpCode.setCreatedAt(System.currentTimeMillis());

        otpCodeRepository.save(otpCode);
        System.out.println("OTP pour " + numero + ": " + otp);
        return otp;
    }


    // Vérifier que l’OTP est correct et non expiré
    public boolean verifyOtp(String numero, String code) {
        Optional<OtpCode> otpCodeOptional = otpCodeRepository.findByNumero(numero);

        if (otpCodeOptional.isPresent()) {
            OtpCode otpCode = otpCodeOptional.get();

            boolean codeValide = otpCode.getCode().equals(code);
            boolean pasExpire = (System.currentTimeMillis() - otpCode.getCreatedAt()) <= EXPIRATION_TIME;

            return codeValide && pasExpire;
        }
        return false;
    }

    // Supprimer l’OTP après validation
    public void clearOtp(String numero) {
        otpCodeRepository.findByNumero(numero).ifPresent(otpCodeRepository::delete);
    }
}
