package com.speleize.alexl.madmax;

import android.provider.BaseColumns;

public class BaseContrat {

    /**
     * Constructeur privé afin de ne pas instancier la classe.
     */
    private BaseContrat() {
    }

    /**
     * Contenu de la table "Vehicle"
     */
    public static class BookingsContrat implements BaseColumns {
        public static final String TABLE_TABLE_BOOKING = "bookings";
        public static final String COLONNE_NOM = "nom";
        public static final String COLONNE_IMAGE = "image";
        public static final String COLONNE_PRIXFINAL = "prixfinal";
        public static final String COLONNE_BEGIN = "begin";
        public static final String COLONNE_END = "end";
    }
}

