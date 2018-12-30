package com.speleize.alexl.madmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookingStep1Activity extends AppCompatActivity {

    private static final String LIEN_IMAGE = "http://s519716619.onlinehome.fr/exchange/madrental/images/";

    private String strBeginBooking;
    private String strEndOfBooking;
    private String strNumberOfDays;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step1);

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
            equipementsNom = equipementsNom + " " + vehicle.equipements.get(i).nom;
        }
        TextView vehicleEquipements = findViewById(R.id.vehicle_equipements);
        vehicleEquipements.setText(equipementsNom);
    }

    public void goToStep2(View view) {
        Intent intent = new Intent(this, BookingStep2Activity.class);
        intent.putExtra("vehicle", vehicle);
        intent.putExtra("beginBooking", strBeginBooking);
        intent.putExtra("endOfBooking", strEndOfBooking);
        intent.putExtra("numberOfDays", strNumberOfDays);
        startActivity(intent);
    }
}
