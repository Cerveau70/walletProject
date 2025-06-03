package com.wave.backend.service;

import com.wave.backend.dto.Login;
import com.wave.backend.dto.UserDTO;
import com.wave.backend.model.User;
import com.wave.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpCodeService otpCodeService;

    public boolean verifierIdentifiants(Login login) {
        User utilisateur = userRepository.findByNumero(login.getNum());
        return utilisateur != null && utilisateur.getMdp().equals(login.getMdp());
    }

    public User connecter(Login login) {
        User user = userRepository.findByNumero(login.getNum());
        if (user != null && user.getMdp().equals(login.getMdp())) {
            return user;
        }
        return null;
    }

    public String register(UserDTO userDTO) {
        if (!otpCodeService.verifyOtp(userDTO.getNumero(), userDTO.getOtpCode())) {
            return "OTP invalide ou expiré.";
        }

        if (userRepository.existsByNumero(userDTO.getNumero())) {
            return "Ce numéro est déjà enregistré.";
        }

        User user = new User();
        user.setNumero(userDTO.getNumero());
        user.setNom(userDTO.getNom());
        user.setMdp(userDTO.getMotDePasse());
        user.setSolde(0.0);

        userRepository.save(user);
        otpCodeService.clearOtp(user.getNumero());

        return "Utilisateur inscrit avec succès !";
    }

    public boolean rechargerCompte(String numero, double montant) {
        User user = userRepository.findByNumero(numero);
        if (user == null) return false;

        user.setSolde(user.getSolde() + montant);
        userRepository.save(user);
        return true;
    }

    public boolean transfererArgent(String numeroSource, String numeroDestinataire, double montant) {
        User source = userRepository.findByNumero(numeroSource);
        User dest = userRepository.findByNumero(numeroDestinataire);

        if (source == null || dest == null || source.getSolde() < montant) return false;

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);

        userRepository.save(source);
        userRepository.save(dest);
        return true;
    }
}
