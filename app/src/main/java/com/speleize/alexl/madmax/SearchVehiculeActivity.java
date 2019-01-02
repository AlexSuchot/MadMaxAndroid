package com.speleize.alexl.madmax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchVehiculeActivity extends AppCompatActivity {

    // Constantes :
    private static final String LIEN = "http://s519716619.onlinehome.fr/exchange/madrental/get-vehicules.php";

    // Vues :
    private RecyclerView recyclerView = null;

    // Adapter :
    private SearchVehiculeAdapter searchVehiculeAdapter = null;

    private String BeginBooking;
    private String EndOfBooking;
    private String NumberOfDays;
    private List<Vehicle> listVehicle = new ArrayList<>();
    private List<Vehicle> listVehiclePromotion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicule);

        if (savedInstanceState == null) {

            SharedPreferences getRentingDate = getSharedPreferences("prefRentingDate", Context.MODE_PRIVATE);
            BeginBooking = getRentingDate.getString("beginBooking", "");
            EndOfBooking = getRentingDate.getString("endOfBooking", "");
            NumberOfDays = getRentingDate.getString("daysBetween","");
        }

        // sauvegarde de la position en shared preferences :
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        // editor.putInt(CLE_POSITION, position);
        editor.apply();

        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();

        // paramètres :
        RequestParams requestParams = new RequestParams();

        client.get(LIEN, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response)
            {
                Gson gson = new Gson();

                String retour = new String(response);
                try
                {
                    JSONArray jsonArray = new JSONArray(retour);


                    for (int i = 0 ; i < jsonArray.length() ; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Vehicle vehicle = gson.fromJson(jsonObject.toString(), Vehicle.class);
                        listVehicle.add(vehicle);
                    }

                    for (int i = 0 ; i < listVehicle.size() ; i++) {
                        if(listVehicle.get(i).promotion != 0) {
                            listVehiclePromotion.add(listVehicle.get(i));
                        }
                    }

                    recyclerView = findViewById(R.id.list_vehicles);

                    // à ajouter pour de meilleures performances :
                    recyclerView.setHasFixedSize(true);

                    // layout manager, décrivant comment les items sont disposés :
                    LinearLayoutManager layoutManager = new LinearLayoutManager(SearchVehiculeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    // adapter :
                    searchVehiculeAdapter = new SearchVehiculeAdapter(SearchVehiculeActivity.this, listVehicle);
                    recyclerView.setAdapter(searchVehiculeAdapter);

                    searchVehiculeAdapter.actualiserVehicles(listVehicle);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e)
            {

            }
        });

        Switch promotionSwitch = findViewById(R.id.promotion_switch);

        promotionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Log.i("Bigeard", listVehiclePromotion.toString());

                    Log.i("Bigeard", "Promotion");

                    // adapter :
                    searchVehiculeAdapter = new SearchVehiculeAdapter(SearchVehiculeActivity.this, listVehiclePromotion);
                    recyclerView.setAdapter(searchVehiculeAdapter);
                } else {
                    Log.i("Bigeard", "Pas Promotion");
                    // adapter :
                    searchVehiculeAdapter = new SearchVehiculeAdapter(SearchVehiculeActivity.this, listVehicle);
                    recyclerView.setAdapter(searchVehiculeAdapter);
                }

            }
        });
    }

    public void onClickItem(Vehicle clickVehicle) {
        Log.i("Bigeard", clickVehicle.nom);
        Intent intent = new Intent(this, BookingStep1Activity.class);
        intent.putExtra("vehicle", clickVehicle);
        /*intent.putExtra("beginBooking", strBeginBooking);
        intent.putExtra("endOfBooking", strEndOfBooking);
        intent.putExtra("numberOfDays", strNumberOfDays);*/
        startActivity(intent);
    }

    public void onClickFilter(View view) {

//        Animation page_slide_vertical_back = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.page_slide_vertical_back);
//
//        Animation page_slide_vertical_out = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.page_slide_vertical_out);

        Button filter = findViewById(R.id.filter);
        LinearLayout filter_block = findViewById(R.id.filter_block);

        Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.line_translate_up);
        filter.startAnimation(localAnimation);
        filter_block.startAnimation(localAnimation);


//        filter.startAnimation(page_slide_vertical_out);

//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.up_filter);
//        set.setTarget(filter);
//        set.start();
//
//        set.setTarget(filter_block);
//        set.start();
//        overridePendingTransition(R.anim.page_slide_vertical_in,
//                R.anim.page_slide_vertical_out);
//        parent.startAnimation(page_slide_vertical_back);

//        AnimatorSet anim = (AnimatorSet) AnimatorInflater.loadAnimator(SearchVehiculeActivity.this, R.animator.up_filter);
    }

}
