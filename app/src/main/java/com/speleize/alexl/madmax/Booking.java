package com.speleize.alexl.madmax;

import java.util.Date;

public class Booking {
    public String nom;
    public String image;
    public String prixjournalierbase;
    public String begin;
    public String end;

    // Constructor :
    public Booking(
            String nom,
            String image,
            String prixjournalierbase,
            String begin,
            String end
    )
    {
        this.nom = nom;
        this.image = image;
        this.prixjournalierbase = prixjournalierbase;
        this.begin = begin;
        this.end = end;
    }
}
