package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button profilButton = null;
    private EditText beginBooking = null;
    private EditText endOfBooking = null;
    private Long age = null;
    SharedPreferences getRentingDate;
    String strNumberOfDays = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Font pour le titre :
        Typeface PermanentMarkerRegular = Typeface.createFromAsset(getAssets(),
                "font/PermanentMarkerRegular.ttf");
        TextView textView = findViewById(R.id.mainTitle);
        textView.setTypeface(PermanentMarkerRegular);

        // Assignement des ID :
        beginBooking = findViewById(R.id.beginBooking);
        endOfBooking = findViewById(R.id.endOfBooking);

        // SHARED PREFERENCES DE LA HOME :
        getRentingDate = getSharedPreferences("prefRentingDate", Context.MODE_PRIVATE);

        String beginBookingDate = getRentingDate.getString("beginBooking", "");
        String endOfBookingDate = getRentingDate.getString("endOfBooking", "");
        strNumberOfDays = getRentingDate.getString("daysBetween", "");


        // SHARED PREFERENCES DU PROFIL :
        SharedPreferences getProfile = getSharedPreferences("prefProfile", Context.MODE_PRIVATE);
        age = getProfile.getLong("userAge", 0);

        beginBooking.setText(beginBookingDate);
        endOfBooking.setText(endOfBookingDate);


        // Renseignement pour l'utilisateur sur la syntaxe de la date :
        beginBooking.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (beginBooking.hasFocus()) {
                    beginBooking.setHint("JJ/MM/AAAA");
                } else {
                    beginBooking.setHint("Date de début de réservation");
                }
            }
        });

        endOfBooking.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (endOfBooking.hasFocus()) {
                    endOfBooking.setHint("JJ/MM/AAAA");
                } else {
                    endOfBooking.setHint("Date de fin de réservation");
                }
            }
        });
    }

    // Boutons de directions vers les différentes pages :
    public void goToBooking(View view) {
        profilButton = findViewById(R.id.bookingButton);
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }

    // Boutons de directions vers les différentes pages :
    public void goToProfile(View view) {
        profilButton = findViewById(R.id.profileButton);
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void searchVehicule(View view) throws ParseException {

        profilButton = findViewById(R.id.searchButton);

        String strBeginBooking = beginBooking.getText().toString();
        String strEndOfBooking = endOfBooking.getText().toString();

        Log.i("Bigeard", strBeginBooking);
        Log.i("Bigeard", strEndOfBooking);
        Log.i("Bigeard", "agadin " + age);

        Pattern regexDate = Pattern.compile("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$");
        Matcher beginMatcher = regexDate.matcher(strBeginBooking);
        Matcher endMatcher = regexDate.matcher(strEndOfBooking);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

        // On récupère l'âge de l'user :
        //Intent getProfileIntent = getIntent();
        //Long ageIntent = getProfileIntent.getLongExtra("ageResult", 0);

        if (age >= 21) {
            try {
                if (beginMatcher.matches() && endMatcher.matches()) {


                    Date dateBegin = format.parse(strBeginBooking);
                    Date dateEnd = format.parse(strEndOfBooking);
                    Date currentDate = new Date();

                    // On vérifie que la date d'aujourd'hui soit avant la date de réservation :
                    if (dateBegin.after(currentDate)) {

                        // On vérifie que la date de réservation de début est avant la date de fin de réservation :
                        if (dateBegin.before(dateEnd)) {

                            Log.i("Bigeard", String.valueOf(dateBegin));
                            Log.i("Bigeard", String.valueOf(dateEnd));

                            long numberOfDays = ((dateEnd.getTime() - dateBegin.getTime()) / (1000 * 60 * 60 * 24));

                            strNumberOfDays = String.valueOf(numberOfDays);

                            Log.i("Bigeard", strNumberOfDays);

                            // SHARED PREFERENCES :
                            SharedPreferences getRentingDate;
                            getRentingDate = getSharedPreferences("prefRentingDate", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = getRentingDate.edit();
                            editor.putString("beginBooking", strBeginBooking);
                            editor.putString("endOfBooking", strEndOfBooking);
                            editor.putString("daysBetween", strNumberOfDays);
                            editor.apply();

                            // INTENT :
                            Intent intent = new Intent(this, SearchVehiculeActivity.class);
                            intent.putExtra("beginBooking", strBeginBooking);
                            intent.putExtra("endOfBooking", strEndOfBooking);
                            intent.putExtra("numberOfDays", strNumberOfDays);
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "La location ne peut pas être après la date de fin de location.", Toast.LENGTH_SHORT).show();
                        }
                    } else if (dateBegin.before(currentDate)) {
                        Toast.makeText(this, "La location ne peut pas être avant la date d'aujourd'hui.", Toast.LENGTH_SHORT).show();

                    } else if (!beginMatcher.matches()) {
                        beginBooking.setError("Mauvais format de date !");
                    } else if (!endMatcher.matches()) {
                        endOfBooking.setError("Mauvais format de date !");
                    }
                }


            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Age minimum requis : 21 ans.", Toast.LENGTH_LONG).show();
        }
    }

}
