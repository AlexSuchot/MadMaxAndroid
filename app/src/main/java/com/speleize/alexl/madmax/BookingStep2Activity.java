package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class BookingStep2Activity extends AppCompatActivity {

    private String BeginBooking;
    private String EndOfBooking;
    private String NumberOfDays;
    private String strOptionsPrix;
    private Vehicle vehicle;
    private Float prixFinal = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step2);

        vehicle = (Vehicle) getIntent().getSerializableExtra("vehicle");

        Bundle extras = getIntent().getExtras();
        SharedPreferences getRentingDate = getSharedPreferences("prefRentingDate", Context.MODE_PRIVATE);
        BeginBooking = getRentingDate.getString("beginBooking", "");
        EndOfBooking = getRentingDate.getString("endOfBooking", "");
        NumberOfDays = getRentingDate.getString("daysBetween","");

        SharedPreferences getPrice = getSharedPreferences("toBookingStep2Activity", Context.MODE_PRIVATE);
        strOptionsPrix = getPrice.getString("optionsPrix", "");

        Log.i("Bigeard", BeginBooking);
        Log.i("Bigeard", EndOfBooking);
        Log.i("Bigeard", NumberOfDays);
//        Log.i("Bigeard", vehicle.nom);
//        Log.i("Bigeard", vehicle.prixjournalierbase.toString());
//        Log.i("Bigeard", vehicle.categorieco2);

        prixFinal = vehicle.prixjournalierbase * Float.parseFloat(NumberOfDays) + Float.parseFloat(strOptionsPrix);
        TextView prix_final = findViewById(R.id.prix_final);
        prix_final.setText("Prix final: " + prixFinal.toString() + " €");
    }

    public void validation(View view) {
        BookingsDAO.ajouterVehicle(this, vehicle, BeginBooking, EndOfBooking, prixFinal.toString());
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
