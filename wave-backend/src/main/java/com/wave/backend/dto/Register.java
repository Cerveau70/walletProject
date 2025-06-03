package com.wave.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Register {
    @NotBlank( message  = "LE nom ne doit pas être vide!")
    private String nom;

    @NotBlank(message =  "Le prenom ne doit pas être pas vide!")
    private String prenom;

    @NotBlank(message =  "Le numero ne doit pas etre vide")
    @Size(min = 10, max = 10, message =  "le numero doit etre 10 chiffre merci!")
    private String numero;

    public Register(){};

    public Register(String nom, String prenom, String numero){
        this.nom = nom;
        this.prenom = nom;
        this.numero = numero;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom= prenom;
    }

    public  String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }
}
