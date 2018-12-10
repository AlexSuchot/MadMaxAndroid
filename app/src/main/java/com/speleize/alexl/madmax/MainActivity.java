package com.speleize.alexl.madmax;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button profilButton = null;
    private EditText beginBooking = null;
    private EditText endOfBooking = null;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assignement des ID :
        beginBooking = findViewById(R.id.beginBooking);
        endOfBooking = findViewById(R.id.endOfBooking);

        beginBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beginBooking.hasFocus()) {
                    beginBooking.setHint("dd/MM/yyyy");
                } else {
                    beginBooking.setHint("Date de début de réservation");
                }
            }

        });

        endOfBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endOfBooking.hasFocus()) {
                    endOfBooking.setHint("dd/MM/yyyy");
                } else {
                    endOfBooking.setHint("Date de fin de réservation");
                }
            }

        });

        Typeface PermanentMarkerRegular = Typeface.createFromAsset(getAssets(),
                "font/PermanentMarkerRegular.ttf");
        TextView textView = findViewById(R.id.mainTitle);
        textView.setTypeface(PermanentMarkerRegular);
    }

    // Boutons de directions vers les différentes pages :
    public void goToProfile(View view) {

        profilButton = findViewById(R.id.profilButton);
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void searchVehicule(View view) {

        String strBeginBooking = beginBooking.getText().toString();
        String strEndOfBooking = endOfBooking.getText().toString();

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        profilButton = findViewById(R.id.searchButton);
        try {
            Date beginBookingDate = formatter.parse(strBeginBooking);
            Date endOfBookingDate = formatter.parse(strEndOfBooking);

            // Vérif si les 2 dates sont rempli :
            if (strBeginBooking.matches("\\d{2}/\\d{2}/\\d{4}") && strEndOfBooking.matches("\\d{2}/\\d{2}/\\d{4}")) {
                Intent intent = new Intent(this, SearchVehiculeActivity.class);
                startActivity(intent);
            } else {
                Log.e("checkEmptyDate", "L'un des edit text est empty");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
