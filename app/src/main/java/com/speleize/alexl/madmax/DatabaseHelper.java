package com.speleize.alexl.madmax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // Constantes :
    private static final String NOM_BASE = "madmax.db";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING + " ("
            + BaseContrat.BookingsContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BaseContrat.BookingsContrat.COLONNE_NOM + " TEXT NOT NULL, "
            + BaseContrat.BookingsContrat.COLONNE_IMAGE + " TEXT NOT NULL, "
            + BaseContrat.BookingsContrat.COLONNE_PRIXJOURNALIERBASE + " FLOAT NOT NULL, "
            + BaseContrat.BookingsContrat.COLONNE_BEGIN + " TEXT NOT NULL, "
            + BaseContrat.BookingsContrat.COLONNE_END + " TEXT NOT NULL "
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
        db.execSQL(CREATE_TABLE_BOOKING);
        db.execSQL("INSERT INTO " + BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING + " VALUES (NULL, 'Buggy', 'zoombuggyjpg', '29', '11/11/2018', '29/12/2018')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING);
        onCreate(db);
    }

}
