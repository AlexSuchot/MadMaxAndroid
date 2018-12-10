package com.speleize.alexl.madmax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.speleize.alexl.madmax.BaseContrat;
import com.speleize.alexl.madmax.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class VehiclesDAO
{

    /**
     * Retourne la liste de mémos.
     * @param context Context
     * @return Liste de Vehicle
     */
    public static List<Vehicle> getListVehicles(Context context)
    {
        // projection (colonnes utilisées après la requète) :
        String[] projection = {BaseContrat.VehiclesContrat._ID,
                BaseContrat.VehiclesContrat.COLONNE_NOM};

        // tri :
        String tri = BaseContrat.VehiclesContrat.COLONNE_NOM + " ASC " ;

        // accès en lecture (query) :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // requête :
        Cursor cursor = db.query(
                BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES,	// table sur laquelle faire la requète
                projection,								// colonnes à retourner
                null,							// colonnes pour la clause WHERE (inactif)
                null,						// valeurs pour la clause WHERE (inactif)
                null,							// GROUP BY (inactif)
                null,							// HAVING (inactif)
                tri,									// ordre de tri
                null);								// LIMIT (inactif)

        // construction de la liste de mémos
        List<Vehicle> listVehicles = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    // conversion des données remontées en un objet métier :
                    listVehicles.add(new Vehicle(cursor.getString(cursor.getColumnIndex(BaseContrat.VehiclesContrat.COLONNE_NOM))));
                    cursor.moveToNext();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }

        return listVehicles;
    }

    /**
     * Ajout d'un mémo en base de données.
     * @param context Context
     * @param nom Nom
     * @return ID vehicle
     */
    public static long ajouterVehicle(Context context, String nom)
    {
        // accès en écriture (insert, update, delete) :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // objet de valeurs :
        ContentValues values = new ContentValues();
        values.put(BaseContrat.VehiclesContrat.COLONNE_NOM, nom);

        // ajout :
        return db.insert(BaseContrat.VehiclesContrat.TABLE_TABLE_VEHICLES, null, values);
    }

}
