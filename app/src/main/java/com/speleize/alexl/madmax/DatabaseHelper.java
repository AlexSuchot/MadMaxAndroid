package com.speleize.alexl.madmax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // Constantes :
    private static final String NOM_BASE = "madmax.db";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE_VEHICLES = "CREATE TABLE " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " ("
            + BaseContrat.VehiclesContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BaseContrat.VehiclesContrat.COLONNE_NOM + " TEXT NOT NULL "
            + ")";


    /**
     * Constructeur.
     * @param context Context
     */
    public DatabaseHelper(Context context)
    {
        super(context, NOM_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_VEHICLES);
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Patates')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Avocat')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Shampooing')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Riz')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Chocolat')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Oeufs')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Lait')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Pain')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Pâtes')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Viande')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Beurre')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Salade')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Oignons')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Raisins')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Sauce')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Croissant')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Pizza')");
//        db.execSQL("INSERT INTO " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES + " VALUES (NULL, 'Chips')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES);
        onCreate(db);
    }

}
