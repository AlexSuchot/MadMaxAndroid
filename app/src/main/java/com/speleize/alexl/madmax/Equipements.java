package com.speleize.alexl.madmax;

import java.io.Serializable;

public class Equipements implements Serializable {
    public Integer id;
    public String nom;

    // Constructor :
    public Equipements(
            Integer id,
            String nom
    )
    {
        this.id = id;
        this.nom = nom;
    }
}
