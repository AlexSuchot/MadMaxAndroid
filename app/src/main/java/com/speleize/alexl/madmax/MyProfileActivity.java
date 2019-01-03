package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyProfileActivity extends AppCompatActivity {

    EditText profilLastName = null;
    EditText profilFirstName = null;
    EditText profilDate = null;

    String strLastName = null;
    String strFirstName = null;
    String strProfilDate = null;
    Long age = null;

    SharedPreferences getProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profilLastName = findViewById(R.id.profilLastName);
        profilFirstName = findViewById(R.id.profilFirstName);
        profilDate = findViewById(R.id.profilDate);

        // On récupère les données du profil lorsqu'on arrive sur la page profil :
        getProfile = getSharedPreferences("prefProfile", Context.MODE_PRIVATE);

        String firstname = getProfile.getString("firstname", "");
        String name = getProfile.getString("lastname", "");
        String strDateOfBirth = getProfile.getString("strDateOfBirth", "");

        profilFirstName.setText(firstname);
        profilLastName.setText(name);
        profilDate.setText(strDateOfBirth);
        age = getProfile.getLong("userAgePrevious",0);

        // Renseignement pour l'utilisateur sur la syntaxe de la date :
        profilDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (profilDate.hasFocus()) {
                    profilDate.setHint("JJ/MM/AAAA");
                } else {
                    profilDate.setHint("Enter your date of birth :");
                }
            }
        });

        profilLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (profilLastName.hasFocus()) {
                    profilLastName.setHint("Enter your name :");
                }
            }
        });

        profilFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (profilFirstName.hasFocus()) {
                    profilFirstName.setHint("Enter your firstname :");
                }
            }
        });


    }

    public void addProfile(View view) {

        strLastName = profilLastName.getText().toString();
        strFirstName = profilFirstName.getText().toString();
        strProfilDate = profilDate.getText().toString();

        Pattern regexDate = Pattern.compile("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$");
        Matcher matcher = regexDate.matcher(strProfilDate);


        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        try {
            if (matcher.matches()) {


                Date dateOfBirth = format.parse(strProfilDate);
                Date currentDate = new Date();


                if (dateOfBirth.before(currentDate)) {

                    age = ((currentDate.getTime() - dateOfBirth.getTime()) / 86400000) / 365;

                    Log.i("Date de naissance", String.valueOf(dateOfBirth));
                    Log.i("Date d'aujourd'hui", String.valueOf(currentDate));
                    Log.i("Age", String.valueOf(age));

                    // INTENT :
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("dateOfBirth", dateOfBirth);
                    intent.putExtra("todaysDate", currentDate);
                    intent.putExtra("userAgeIntent", age);

                    // SHARED PREFERENCES :

                    SharedPreferences getProfile;
                    getProfile = getSharedPreferences("prefProfile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = getProfile.edit();
                    editor.putString("firstname", strLastName);
                    editor.putString("lastname", strFirstName);
                    editor.putString("strDateOfBirth", strProfilDate);
                    editor.putLong("userAge", age);
                    editor.apply();

                    Toast.makeText(this, "Profile successfully update ! ", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Wrong date of birth, can't be set after the current date.", Toast.LENGTH_SHORT).show();
                }

            } else if (!matcher.matches()) {
                profilDate.setError("Wrong date format !");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}

