package com.speleize.alexl.madmax;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchVehiculeAdapter extends RecyclerView.Adapter<SearchVehiculeAdapter.VehicleViewHolder>
{

    // Activité :
    private SearchVehiculeActivity searchVehiculeActivity = null;

    // list d'objets métier :
    private List<Vehicle> listVehicle = null;

    // Image
    private static final String LIEN_IMAGE = "http://s519716619.onlinehome.fr/exchange/madrental/images/";
    private ImageView vehicleImage = null;

    /**
     * Constructeur.
     * @param searchVehiculeActivity SearchVehiculeActivity
     * @param listVehicle list de vehicle
     */
    public SearchVehiculeAdapter(SearchVehiculeActivity searchVehiculeActivity, List<Vehicle> listVehicle)
    {
        this.searchVehiculeActivity = searchVehiculeActivity;
        this.listVehicle = listVehicle;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewVehicle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_vehicule, parent, false);
        return new VehicleViewHolder(viewVehicle);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position)
    {
        holder.vehicleNom.setText(listVehicle.get(position).nom);
        holder.vehiclePrixJournalierBase.setText(listVehicle.get(position).prixjournalierbase.toString() + " € / jour");
        holder.vehicleCategorieCo2.setText( "Catégorie CO2: " + listVehicle.get(position).categorieco2);

        Integer promotion = listVehicle.get(position).promotion;

        if(promotion != 0) {
            holder.vehiclePromotion.setVisibility(View.VISIBLE);
            holder.vehiclePromotion.setText(promotion.toString() + '%');
        }

        // chargement de l'image :
        Picasso.with(searchVehiculeActivity)
                .load(LIEN_IMAGE + listVehicle.get(position).image)
                .fit()
                .centerInside()
                .into(vehicleImage);

    }

    @Override
    public int getItemCount()
    {
        return listVehicle.size();
    }

    /**
     * Ajout d'un mémo à la list.
     * @param listVehicle list de Vehicle
     */
        public void actualiserVehicles(List<Vehicle> listVehicle)
    {
        this.listVehicle = listVehicle;
        notifyDataSetChanged();
    }

    /**
     * Retourne le Vehicle à la position indiquée.
     * @param position Position dans la list
     * @return Vehicle
     */
    public Vehicle getVehicleParPosition(int position)
    {
        return listVehicle.get(position);

    }


    /**
     * ViewHolder.
     */
    class VehicleViewHolder extends RecyclerView.ViewHolder
    {
        TextView vehiclePromotion = null;
        TextView vehicleNom = null;
        TextView vehiclePrixJournalierBase = null;
        TextView vehicleCategorieCo2 = null;

        VehicleViewHolder(final View itemView) {
            super(itemView);

            vehicleImage = itemView.findViewById(R.id.vehicle_image);

            vehiclePromotion = itemView.findViewById(R.id.vehicle_promotion);

            vehicleNom = itemView.findViewById(R.id.vehicle_nom);
            vehiclePrixJournalierBase = itemView.findViewById(R.id.vehicle_prixjournalierbase);
            vehicleCategorieCo2 = itemView.findViewById(R.id.vehicle_categorieco2);

            // listener :
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.i("Bigeard", String.valueOf(getAdapterPosition()));
                    Vehicle clickVehicle = listVehicle.get(getAdapterPosition());
                    Log.i("Bigeard", String.valueOf(clickVehicle));

                    searchVehiculeActivity.onClickItem(clickVehicle);
                }
            });
        }
    }
}
