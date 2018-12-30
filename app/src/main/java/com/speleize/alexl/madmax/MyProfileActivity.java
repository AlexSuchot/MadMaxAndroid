package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyProfileActivity extends AppCompatActivity {

    EditText profilLastName = null;
    EditText profilFirstName = null;
    EditText profilDate = null;

    String strLastName = null;
    String strFirstName = null;
    String strProfilDate = null;

    SharedPreferences getProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profilLastName = findViewById(R.id.profilLastName);
        profilFirstName = findViewById(R.id.profilFirstName);
        profilDate = findViewById(R.id.profilDate);

        getProfile = getSharedPreferences("prefProfile", Context.MODE_PRIVATE);

        String firstname = getProfile.getString("firstname", "");
        String name = getProfile.getString("lastname", "");

        profilFirstName.setText(firstname);
        profilLastName.setText(name);

        // Renseignement pour l'utilisateur sur la syntaxe de la date :
        profilDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (profilDate.hasFocus()) {
                    profilDate.setHint("MM/dd/yyyy");
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


        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if (matcher.matches()) {


                Date dateOfBirth = format.parse(strProfilDate);
                Date currentDate = new Date();

                long age = ((currentDate.getTime() - dateOfBirth.getTime()) / 86400000) / 365;

               // String ageValue = String.valueOf(age);

                Log.i("Date de naissance", String.valueOf(dateOfBirth));
                Log.i("Date d'aujourd'hui", String.valueOf(currentDate));
                Log.i("Age", String.valueOf(age));


                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("dateOfBirth", dateOfBirth);
                intent.putExtra("todaysDate", currentDate);
                intent.putExtra("ageResult", age);

                startActivity(intent);

                SharedPreferences getProfile;
                getProfile = getSharedPreferences("prefProfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = getProfile.edit();
                editor.putString("firstname", strLastName);
                editor.putString("lastname", strFirstName);
                editor.apply();

                startActivity(intent);

            } else if (!matcher.matches()) {
                profilDate.setError("Mauvais format de date !");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}

