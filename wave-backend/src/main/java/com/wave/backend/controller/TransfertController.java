package com.wave.backend.controller;

import com.wave.backend.dto.Transfert;
import com.wave.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transfert")
public class TransfertController {

    private UserService userService;

    public TransfertController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> transferer(@RequestBody Transfert transfert) {
        boolean success = userService.transfererArgent(
                transfert.getNumeroSource(),
                transfert.getNumeroDestinataire(),
                transfert.getMontant()
        );

        if (success) {
            return ResponseEntity.ok("Transfert effectué !");
        } else {
            return ResponseEntity.badRequest().body("Échec du transfert. Vérifie le solde ou les numéros.");
        }
    }
}
