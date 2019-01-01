package com.speleize.alexl.madmax;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingsDAO
{

    /**
     * Retourne la liste des bookings.
     * @param context Context
     * @return Liste de Vehicle
     */
    public static List<Booking> getListBookings(Context context)
    {
        // projection (colonnes utilisées après la requète) :
        String[] projection = {BaseContrat.BookingsContrat._ID,
                BaseContrat.BookingsContrat.COLONNE_NOM,
                BaseContrat.BookingsContrat.COLONNE_IMAGE,
                BaseContrat.BookingsContrat.COLONNE_PRIXJOURNALIERBASE,
                BaseContrat.BookingsContrat.COLONNE_BEGIN,
                BaseContrat.BookingsContrat.COLONNE_END,

        };

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
                            cursor.getString(cursor.getColumnIndex( BaseContrat.BookingsContrat.COLONNE_PRIXJOURNALIERBASE )),
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
     * @param vehicle Vehicle
     * @param strBeginBooking BeginBooking
     * @param strEndOfBooking EndOfBooking
     * @return ID vehicle
     */
    public static long ajouterVehicle(Context context, Vehicle vehicle, String strBeginBooking, String strEndOfBooking)
    {
        // accès en écriture (insert, update, delete) :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // objet de valeurs :
        ContentValues values = new ContentValues();
        values.put(BaseContrat.BookingsContrat.COLONNE_NOM, vehicle.nom);
        values.put(BaseContrat.BookingsContrat.COLONNE_IMAGE, vehicle.image);
        values.put(BaseContrat.BookingsContrat.COLONNE_PRIXJOURNALIERBASE, vehicle.prixjournalierbase);
        values.put(BaseContrat.BookingsContrat.COLONNE_BEGIN, strBeginBooking);
        values.put(BaseContrat.BookingsContrat.COLONNE_END, strEndOfBooking);

        // ajout :
        return db.insert(BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING, null, values);
    }

    public static void getOutDate(Context context) throws ParseException {
        // accès à la base de données :
        List<Booking> listBooking = BookingsDAO.getListBookings(context);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        Date today = new Date();

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Log.i("Bigeard", "Coucou");
        Log.i("Bigeard", String.valueOf(listBooking));

        for (int i = 0; i < listBooking.size(); i++) {
            Log.i("Bigeard", "In for");

            Date dateEnd = format.parse(listBooking.get(i).end);
            Date dateToday = format.parse(String.valueOf(today));

            Log.i("Bigeard", String.valueOf(dateEnd));
            Log.i("Bigeard", String.valueOf(dateToday));
            if ( dateEnd.getTime() > dateToday.getTime() ){
                db.delete(BaseContrat.BookingsContrat.TABLE_TABLE_BOOKING, BaseContrat.BookingsContrat.COLONNE_NOM + "=" + listBooking.get(i).nom, null);
                Log.i("Bigeard", "getOutDate: Delete");
            }
        }
    }
}
