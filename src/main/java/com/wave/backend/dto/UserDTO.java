package com.wave.backend.dto;

public class UserDTO {
    private String nom;
    private String numero;
    private String motDePasse;
    private String otpCode;

    public UserDTO(){};

    public UserDTO(String nom, String num, String mdp, String otpcode){
        this.nom = nom;
        this.numero = num;
        this.motDePasse = mdp;
        this.otpCode = otpcode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    // Getters et Setters
}
