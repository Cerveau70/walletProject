package com.wave.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name =  "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Le nom ne doit pas être vide!")
    private String  nom;

    private String prenom;

    @Size(min = 10, max = 10, message =  " le numero doit contenir 10 chiffres!")
    private String numero;


    @NotBlank(message = " Le mot de passe ne doit pas être vide!")
    @Size(min = 3)
    @Column(name = "motDePasse")
    private String mdp;


    private  Boolean isVerified = false;

    private Double solde = 0.0;

    public User(){};

    public User(Long id, String nom, String prenom, String numero, String mdp,Boolean isVerified, Double solde){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.mdp = mdp;
        this.isVerified = isVerified;
        this.solde = solde;
    }

//    Logique pour setters et getters

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void  setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getNumero(){
        return numero;
    }

    public void  setNumero(String num){
        this.numero = num;
    }

    public String getMdp(){
        return mdp;
    }

    public void setMdp(String mdp){
        this.mdp = mdp;
    }
    public Boolean getIsVerified() {
        return isVerified;
    }

    public void  setIsVerified(Boolean isv){
        this.isVerified = isv;
    }

    public Double getSolde(){
        return solde;
    }

    public void setSolde(Double solde){
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero='" + numero + '\'' +
                ", isVerified=" + isVerified +
                ", solde=" + solde +
                '}';
    }


}
