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
    private List<Booking> listBooking = null;


    /**
     * Constructeur.
     * @param bookingActivity BookingActivity
     * @param listBooking list de mémos
     */
    public BookingAdapter(BookingActivity bookingActivity, List<Booking> listBooking)
    {
        this.bookingActivity = bookingActivity;
        this.listBooking = listBooking;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewVehicle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new VehicleViewHolder(viewVehicle);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position)
    {
        holder.textViewWording.setText(listBooking.get(position).nom);
    }

    @Override
    public int getItemCount()
    {
        return listBooking.size();
    }

    /**
     * Ajout d'un mémo à la list.
     * @param listBooking list de Vehicle
     */
    public void actualiserVehicles(List<Booking> listBooking)
    {
        this.listBooking = listBooking;
        notifyDataSetChanged();
    }

    /**
     * Retourne le Vehicle à la position indiquée.
     * @param position Position dans la list
     * @return Vehicle
     */
    public Booking getVehicleParPosition(int position)
    {
        return listBooking.get(position);
    }


    /**
     * ViewHolder.
     */
    class VehicleViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewWording = null;
        VehicleViewHolder(final View itemView) {
            super(itemView);
            textViewWording = itemView.findViewById(R.id.vehicle_nom);
        }
    }
}
