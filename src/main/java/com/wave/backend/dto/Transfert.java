package com.wave.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Transfert {
    @NotBlank(message = "Le numéro source est obligatoire")
    private String numeroSource;

    @NotBlank(message = "Le numéro destinataire est obligatoire")
    private String numeroDestinataire;

    @Min(value = 1, message = "Le montant doit être supérieur à zéro")
    private double montant;

    public String getNumeroSource() {
        return numeroSource;
    }

    public void setNumeroSource(String numeroSource) {
        this.numeroSource = numeroSource;
    }

    public String getNumeroDestinataire() {
        return numeroDestinataire;
    }

    public void setNumeroDestinataire(String numeroDestinataire) {
        this.numeroDestinataire = numeroDestinataire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
