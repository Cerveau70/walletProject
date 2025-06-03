package com.wave.backend.model;

import jakarta.persistence.*;

@Entity
public class OtpCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String code; // 6 chiffres aléatoires

    private Long createdAt = System.currentTimeMillis(); // date de création en millisecondes

    public OtpCode() {}

    public OtpCode(String numero, String code) {
        this.numero = numero;
        this.code = code;
        this.createdAt = System.currentTimeMillis();
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OtpCode{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
