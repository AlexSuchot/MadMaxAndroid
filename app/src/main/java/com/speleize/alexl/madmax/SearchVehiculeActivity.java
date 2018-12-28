package com.speleize.alexl.madmax;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicule);
        Bundle extras = getIntent().getExtras();
        String newString = extras.getString("beginBooking");
        String newString2 = extras.getString("endOfBooking");
        String newString3 = extras.getString("numberOfDays");
        Log.i("yolo",newString);
        Log.i("yolo2",newString2);
        Log.i("yolo3",newString3);

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
                    List<Vehicle> listVehicle = new ArrayList<>();

                    for (int i = 0 ; i < jsonArray.length() ; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Vehicle vehicle = gson.fromJson(jsonObject.toString(), Vehicle.class);
                        listVehicle.add(vehicle);
                    }
                    Log.i("Bigeard", listVehicle.toString());

                    for (Vehicle vehicle : listVehicle)
                    {

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
    }
}
