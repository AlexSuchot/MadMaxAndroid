package com.speleize.alexl.madmax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class BookingStep2Activity extends AppCompatActivity {

    private String strBeginBooking;
    private String strEndOfBooking;
    private String strNumberOfDays;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step2);

        vehicle = (Vehicle)getIntent().getSerializableExtra("vehicle");

        Bundle extras = getIntent().getExtras();
        strBeginBooking = extras.getString("beginBooking");
        strEndOfBooking = extras.getString("endOfBooking");
        strNumberOfDays = extras.getString("numberOfDays");
        Log.i("Bigeard",strBeginBooking);
        Log.i("Bigeard",strEndOfBooking);
        Log.i("Bigeard",strNumberOfDays);
        Log.i("Bigeard", vehicle.nom);
        Log.i("Bigeard", vehicle.prixjournalierbase.toString());
        Log.i("Bigeard", vehicle.categorieco2);

        Float prixFianl = vehicle.prixjournalierbase * Float.parseFloat(strNumberOfDays);
        TextView prix_final = findViewById(R.id.prix_final);
        prix_final.setText("Prix final: " + prixFianl.toString() + " €");
    }

    public void validation(View view) {
    }
}
