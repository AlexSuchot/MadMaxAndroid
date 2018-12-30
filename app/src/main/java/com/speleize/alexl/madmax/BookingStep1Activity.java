package com.speleize.alexl.madmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BookingStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step1);
    }

    public void goToStep2(View view) {
        Intent intent = new Intent(this, BookingStep2Activity.class);
        startActivity(intent);
    }
}
