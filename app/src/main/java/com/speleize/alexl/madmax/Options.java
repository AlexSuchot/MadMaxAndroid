package com.speleize.alexl.madmax;

public class Options {

    public String nom;
    public Boolean option;
    public Float prix;

    // Constructor :
    public Options(
            String nom,
            Boolean option,
            Float prix
    )
    {
        this.nom = nom;
        this.option = option;
        this.prix = prix;
    }
}
