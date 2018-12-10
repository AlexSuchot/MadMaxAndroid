package com.speleize.alexl.madmax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class BookingActivity extends AppCompatActivity
    {

        // Vues :
        private RecyclerView recyclerView = null;

        // Adapter :
        private BookingAdapter bookingAdapter = null;


        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            // init :
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_booking);

            // création de la base de données, si inexistante :
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.getReadableDatabase();

            // accès à la base de données :
            List<Vehicle> listVehicle = VehiclesDAO.getListVehicles(this);

            // vues :
            recyclerView = findViewById(R.id.list_vehicles);
//            editTextVehicle = findViewById(R.id.saisie_vehicle);

            // à ajouter pour de meilleures performances :
            recyclerView.setHasFixedSize(true);

            // layout manager, décrivant comment les items sont disposés :
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // adapter :
            bookingAdapter = new BookingAdapter(this, listVehicle);
            recyclerView.setAdapter(bookingAdapter);
        }
    }

