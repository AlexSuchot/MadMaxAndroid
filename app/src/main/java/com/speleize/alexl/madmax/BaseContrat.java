package com.speleize.alexl.madmax;

import android.provider.BaseColumns;

public class BaseContrat
{

    /**
     * Constructeur privé afin de ne pas instancier la classe.
     */
    private BaseContrat() {}

    /**
     * Contenu de la table "Vehicle"
     */
    public static class VehiclesContrat implements BaseColumns
    {
        public static final String TABLE_TABLE_VEHICLES = "vehicles";
        public static final String COLONNE_NOM = "nom";
    }

}
