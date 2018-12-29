package com.speleize.alexl.madmax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

    }

    public void addProfile(View view) {

        EditText profilLastName = findViewById(R.id.profilLastName);
        EditText profilFirstName = findViewById(R.id.profilFirstName);
        EditText profilDate = findViewById(R.id.profilDate);

        Log.i("Bigeard", String.valueOf(profilLastName.getText()));
        Log.i("Bigeard", String.valueOf(profilFirstName.getText()));
        Log.i("Bigeard", String.valueOf(profilDate.getText()));

    }
}
