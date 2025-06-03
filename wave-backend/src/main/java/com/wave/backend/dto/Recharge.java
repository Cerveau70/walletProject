package com.wave.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Recharge {
    @NotBlank(message = "Le numéro est obligatoire")
    private String numero;

    @Min(value = 1, message = "Le montant doit être supérieur à zéro")
    private double montant;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}





