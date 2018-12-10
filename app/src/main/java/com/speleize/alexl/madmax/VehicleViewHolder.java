package com.speleize.alexl.madmax;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VehicleViewHolder extends RecyclerView.ViewHolder
{
    // TextView intitulé :
    public TextView textViewWordingVehicle;
    // Constructeur :
    public VehicleViewHolder(View itemView)
    {
        super(itemView);
        textViewWordingVehicle = itemView.findViewById(R.id.wording_vehicle);
    }
}