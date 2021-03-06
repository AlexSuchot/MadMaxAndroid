package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class BookingStep1Activity extends AppCompatActivity {

    // Vues :
    private RecyclerView recyclerView = null;

    // Adapter :
    private OptionsAdapter optionsAdapter = null;

    private static final String LIEN_IMAGE = "http://s519716619.onlinehome.fr/exchange/madrental/images/";


    private String BeginBooking;
    private String EndOfBooking;
    private String NumberOfDays;
    private Vehicle vehicle;
    private Float optionsPrix = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step1);



        SharedPreferences getRentingDate = getSharedPreferences("prefRentingDate", Context.MODE_PRIVATE);
        BeginBooking = getRentingDate.getString("beginBooking", "");
        EndOfBooking = getRentingDate.getString("endOfBooking", "");
        NumberOfDays = getRentingDate.getString("daysBetween","");

        SharedPreferences getVehicle = getSharedPreferences("prefVehicle", Context.MODE_PRIVATE);
        String json = getVehicle.getString("vehicle", "");
        Gson gson = new Gson();
        vehicle = gson.fromJson(json, Vehicle.class);


        TextView vehicleNom = findViewById(R.id.vehicle_nom);
        vehicleNom.setText(vehicle.nom);

        // chargement de l'image :
        ImageView vehicleImage = findViewById(R.id.vehicle_image);
        Picasso.with(this)
                .load(LIEN_IMAGE + vehicle.image)
                .fit()
                .centerInside()
                .into(vehicleImage);

        TextView vehiclePrixjournalierbase = findViewById(R.id.vehicle_prixjournalierbase);
        vehiclePrixjournalierbase.setText(vehicle.prixjournalierbase.toString() + " € / jour");

        TextView vehicleCategorieco2 = findViewById(R.id.vehicle_categorieco2);
        vehicleCategorieco2.setText(vehicle.categorieco2);

        String equipementsNom = "";
        for (int i = 0 ; i < vehicle.equipements.size() ; i++) {
            equipementsNom = equipementsNom + vehicle.equipements.get(i).nom + "\n";
        }
        TextView vehicleEquipements = findViewById(R.id.vehicle_equipements);
        vehicleEquipements.setText(equipementsNom);

        recyclerView = findViewById(R.id.list_options);

        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);

        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(BookingStep1Activity.this);
        recyclerView.setLayoutManager(layoutManager);

        // adapter :
        optionsAdapter = new OptionsAdapter(BookingStep1Activity.this, vehicle.options);
        recyclerView.setAdapter(optionsAdapter);

        if(vehicle.options.isEmpty()){
            LinearLayout option = findViewById(R.id.options);
            option.setVisibility(View.INVISIBLE);
        }

    }

    public void optionPriceChange(Float optionsPrix){
        this.optionsPrix = optionsPrix;
    }

    public void goToStep2(View view) {
        Intent intent = new Intent(this, BookingStep2Activity.class);
        intent.putExtra("vehicle", vehicle);

        // SHARED PREFERENCES :
        SharedPreferences getRentingDate;
        getRentingDate = getSharedPreferences("toBookingStep2Activity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getRentingDate.edit();
        editor.putString("optionsPrix", optionsPrix.toString());
        editor.apply();

        startActivity(intent);
    }
}
