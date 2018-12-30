package com.speleize.alexl.madmax;

import java.io.Serializable;

public class Options implements Serializable {
    public Integer id;
    public String nom;
    public Float prix;

    // Constructor :
    public Options(
            Integer id,
            String nom,
            Float prix
    )
    {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }
}
