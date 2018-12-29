package com.speleize.alexl.madmax;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.VehicleViewHolder>
{

    // Activité :
    private BookingActivity bookingActivity = null;

    // list d'objets métier :
    private List<Booking> listBooking = null;

    // Image
    private static final String LIEN_IMAGE = "http://www.denis-fremond.com/photos_art/20062014195748u38892506.JPG";
    private ImageView vehicleImage = null;


    /**
     * Constructeur.
     * @param bookingActivity BookingActivity
     * @param listBooking list des bookings
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
        holder.vehicleNom.setText(listBooking.get(position).nom + " - " + listBooking.get(position).prixjournalierbase + " €");
        holder.vehicleBegin.setText("début: " + listBooking.get(position).begin);
        holder.vehicleEnd.setText("fin : " + listBooking.get(position).end);

        // chargement de l'image :
        Picasso.with(bookingActivity)
                .load(LIEN_IMAGE)
                .fit()
                .centerInside()
                .into(vehicleImage);
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
        TextView vehicleNom = null;
        TextView vehiclePrixjournalierbase = null;

        TextView vehicleBegin = null;
        TextView vehicleEnd = null;

        VehicleViewHolder(final View itemView) {
            super(itemView);

            vehicleImage = itemView.findViewById(R.id.vehicle_image);

            vehicleNom = itemView.findViewById(R.id.vehicle_nom);

            vehicleBegin = itemView.findViewById(R.id.vehicle_begin);
            vehicleEnd = itemView.findViewById(R.id.vehicle_end);
        }
    }
}
