package com.speleize.alexl.madmax;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    public void searchVehicule(View view) {

        String strBeginBooking = beginBooking.getText().toString();
        String strEndOfBooking = endOfBooking.getText().toString();

        // Expression régulière récupère la date et la vérifie si elle est au bon format :
        Pattern regexDate = Pattern.compile("^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        String regexString = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        Matcher  matcherBegin = regexDate.matcher(strBeginBooking);
        Matcher matcherEnd = regexDate.matcher(strEndOfBooking);
        profilButton = findViewById(R.id.searchButton);
        String beginBookingToString = matcherBegin.toString();
        String endOfBookingToString = matcherEnd.toString();
        
        try {

            // Vérif si les 2 dates sont rempli :
            if (matcherBegin.matches() && matcherEnd.matches()) {
                Intent intent = new Intent(this, SearchVehiculeActivity.class);

                intent.putExtra("beginBooking", strBeginBooking);
                intent.putExtra("endOfBooking", strEndOfBooking);

                //long numberOfDays = dateBeginBooking.getTime() - dateEndOfBooking.getTime();
                //intent.putExtra("numberOfDays", numberOfDays);
                startActivity(intent);
                // On récupère le nombre de jour :
                Date dateBeginBooking = regex.parse(strBeginBooking);
                Date dateEndOfBooking = regex.parse(strEndOfBooking);


                // On put les valeurs des dates pour les récupérer plus tard :




            } else if (!matcherBegin.matches()) {
                beginBooking.setError("Mauvais format de date");
            } else if (matcherEnd.matches()) {
                endOfBooking.setError("Mauvais format de date");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
