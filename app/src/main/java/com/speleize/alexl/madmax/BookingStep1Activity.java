package com.speleize.alexl.madmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class BookingStep1Activity extends AppCompatActivity {

//    String strBeginBooking;
//    String strEndOfBooking;
//    String strNumberOfDays;
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step1);

        vehicle = (Vehicle)getIntent().getSerializableExtra("vehicle");

//        Bundle extras = getIntent().getExtras();
//        strBeginBooking = extras.getString("beginBooking");
//        strEndOfBooking = extras.getString("endOfBooking");
//        strNumberOfDays = extras.getString("numberOfDays");
//        Log.i("Bigeard",strBeginBooking);
//        Log.i("Bigeard",strEndOfBooking);
//        Log.i("Bigeard",strNumberOfDays);
        Log.i("Bigeard", vehicle.nom);

    }

    public void goToStep2(View view) {
        Intent intent = new Intent(this, BookingStep2Activity.class);
        startActivity(intent);
    }
}
