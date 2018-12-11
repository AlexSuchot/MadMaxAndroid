package com.speleize.alexl.madmax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.speleize.alexl.madmax.BaseContrat;
import com.speleize.alexl.madmax.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BookingsDAO
{

    /**
     * Retourne la liste de mémos.
     * @param context Context
     * @return Liste de Vehicle
     */
    public static List<Booking> getListBookings(Context context)
    {
        // projection (colonnes utilisées après la requète) :
        String[] projection = {BaseContrat.BookingsContrat._ID,
                BaseContrat.BookingsContrat.COLONNE_NOM};

        // tri :
        String tri = BaseContrat.BookingsContrat.COLONNE_NOM + " ASC " ;

        // accès en lecture (query) :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // requête :
        Cursor cursor = db.query(
                BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING,	// table sur laquelle faire la requète
                projection,								// colonnes à retourner
                null,							// colonnes pour la clause WHERE (inactif)
                null,						// valeurs pour la clause WHERE (inactif)
                null,							// GROUP BY (inactif)
                null,							// HAVING (inactif)
                tri,									// ordre de tri
                null);								// LIMIT (inactif)

        // construction de la liste de mémos
        List<Booking> listBooking = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    // conversion des données remontées en un objet métier :
                    listBooking.add(new Booking(
                            cursor.getString(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_NOM )),
                            cursor.getString(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_IMAGE )),
                            cursor.getFloat(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_PRIXJOURNALIERBASE )),
                            cursor.getString(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_BEGIN )),
                            cursor.getString(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_END ))
                            ));
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

        return listBooking;
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
        values.put(BaseContrat.BookingsContrat.COLONNE_NOM, nom);

        // ajout :
        return db.insert(BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING, null, values);
    }
}
