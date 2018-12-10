package com.speleize.alexl.madmax;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.VehicleViewHolder>
{

    // Activité :
    private BookingActivity bookingActivity = null;

    // list d'objets métier :
    private List<Vehicle> listVehicle = null;


    /**
     * Constructeur.
     * @param bookingActivity BookingActivity
     * @param listVehicle list de mémos
     */
    public BookingAdapter(BookingActivity bookingActivity, List<Vehicle> listVehicle)
    {
        this.bookingActivity = bookingActivity;
        this.listVehicle = listVehicle;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewVehicle = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item_list, parent, false);
        return new VehicleViewHolder(viewVehicle);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position)
    {
        holder.textViewWording.setText(listVehicle.get(position).nom);
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
        TextView textViewWording = null;
        VehicleViewHolder(final View itemView) {
            super(itemView);
            textViewWording = itemView.findViewById(R.id.wording_vehicle);
        }
    }
}
