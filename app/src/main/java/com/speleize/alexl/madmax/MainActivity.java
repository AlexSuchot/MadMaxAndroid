package com.speleize.alexl.madmax;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button profilButton = null;
    private EditText beginBooking = null;
    private EditText endOfBooking = null;

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

        // Renseignement pour l'utilisateur sur la syntaxe de la date :
        beginBooking.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (beginBooking.hasFocus()) {
                    beginBooking.setHint("dd/MM/yyyy");
                } else {
                    beginBooking.setHint("Date de début de réservation");
                }
            }
        });

        endOfBooking.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (endOfBooking.hasFocus()) {
                    endOfBooking.setHint("dd/MM/yyyy");
                } else {
                    endOfBooking.setHint("Date de fin de réservation");
                }
            }
        });
    }

    // Boutons de directions vers les différentes pages :
    public void goToProfile(View view) {

        profilButton = findViewById(R.id.profilButton);
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void searchVehicule(View view) throws ParseException {

        profilButton = findViewById(R.id.searchButton);

        String strBeginBooking = beginBooking.getText().toString();
        String strEndOfBooking = endOfBooking.getText().toString();

        Log.i("Bigeard",strBeginBooking);
        Log.i("Bigeard",strEndOfBooking);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date dateBegin = format.parse(strBeginBooking);
            Date dateEnd = format.parse(strEndOfBooking);

            Log.i("Bigeard", String.valueOf(dateBegin));
            Log.i("Bigeard", String.valueOf(dateEnd));

            long numberOfDays = dateEnd.getTime() - dateBegin.getTime();

            String stringNumberOfDays = String.valueOf(numberOfDays);

            Log.i("Bigeard", String.valueOf(numberOfDays));
            Log.i("Bigeard", stringNumberOfDays);

            Intent intent = new Intent(this, SearchVehiculeActivity.class);
            intent.putExtra("beginBooking", strBeginBooking);
            intent.putExtra("endOfBooking", strEndOfBooking);
            intent.putExtra("numberOfDays", stringNumberOfDays);
            startActivity(intent);

            // On récupère le nombre de jour :

            // On put les valeurs des dates pour les récupérer plus tard :

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
