package com.speleize.alexl.madmax;

import java.util.List;

public class Vehicle {
    public String nom;
    public String image;
    public Integer disponible;
    public Float prixjournalierbase;
    public Integer promotion;
    public Integer agemin;
    public String categorieco2;
    public List<String> equipements;
    public List<Options> options;


    // Constructor :
    public Vehicle(
            String nom,
            String image,
            Integer disponible,
            Float prixjournalierbase,
            Integer promotion,
            Integer agemin,
            String categorieco2,
            List<String> equipements,
            List<Options> options
    )
    {
        this.nom = nom;
        this.image = image;
        this.disponible = disponible;
        this.prixjournalierbase = prixjournalierbase;
        this.promotion = promotion;
        this.agemin = agemin;
        this.categorieco2 = categorieco2;
        this.equipements = equipements;
        this.options = options;
    }
}