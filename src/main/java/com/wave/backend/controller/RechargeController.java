package com.wave.backend.controller;

import com.wave.backend.dto.Recharge;
import com.wave.backend.model.User;
import com.wave.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    private UserService userService;

    public RechargeController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> recharger(@RequestBody Recharge rechargeDTO) {
        boolean success = userService.rechargerCompte(rechargeDTO.getNumero(), rechargeDTO.getMontant());

        if (success) {
            return ResponseEntity.ok("Rechargement effectué !");
        } else {
            return ResponseEntity.badRequest().body("Échec du rechargement. Numéro non trouvé.");
        }
    }

}
