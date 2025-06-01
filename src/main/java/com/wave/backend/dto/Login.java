package com.wave.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login {
    @NotBlank(message = "Le numero ne doit pas etre null")
    @Size(min = 10, max =10, message = "Le numero doit etre 10 chifffre !")
    @Column(name = "numero")
    private String num;

    @NotBlank(message = "Le mot ne doit pas etre null")
    @Column(name = "motDePasse")
    @Size(min  = 3, message = "le mot de passe doit etre au moin 03 chiffres")
    private String mdp;


    public Login(){};

    public Login(String num, String mdp){
        this.num = num;
        this.mdp = mdp;
    }

    public String getNum(){
        return num;
    }

    public void  setNum(String num){
        this.num = num;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
