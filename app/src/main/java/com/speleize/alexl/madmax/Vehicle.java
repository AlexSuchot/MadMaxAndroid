package com.speleize.alexl.madmax;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Vehicle implements Serializable {
    public Integer id;
    public String nom;
    public String image;
    public Integer disponible;
    public Float prixjournalierbase;
    public Integer promotion;
    public Integer agemin;
    public String categorieco2;
    public List<Equipements> equipements;
    public List<Options> options;


    // Constructor :
    public Vehicle (
            Integer id,
            String nom,
            String image,
            Integer disponible,
            Float prixjournalierbase,
            Integer promotion,
            Integer agemin,
            String categorieco2,
            List<Equipements> equipements,
            List<Options> options
    )
    {
        this.id = id;
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