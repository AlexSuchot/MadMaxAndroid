package com.speleize.alexl.madmax;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.VehicleViewHolder>
{
    private Float optionsPrix = 0f;

    // Activité :
    private BookingStep1Activity bookingStep1Activity = null;

    // list d'objets métier :
    private List<Options> listOption = null;

    /**
     * Constructeur.
     * @param BookingStep1Activity BookingStep1Activity
     * @param listOption list de option
     */
    public OptionsAdapter(BookingStep1Activity bookingStep1Activity, List<Options> listOption)
    {
        this.bookingStep1Activity = bookingStep1Activity;
        this.listOption = listOption;

    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewOption = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
        return new VehicleViewHolder(viewOption);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position)
    {
        holder.optionText.setText(listOption.get(position).nom + " : " + listOption.get(position).prix + " €");
    }

    @Override
    public int getItemCount()
    {
        return listOption.size();
    }

    /**
     * Ajout d'un mémo à la list.
     * @param listOption list de Option
     */
        public void actualiserOptions(List<Options> listOption)
    {
        this.listOption = listOption;
        notifyDataSetChanged();
    }

    /**
     * Retourne le Vehicle à la position indiquée.
     * @param position Position dans la list
     * @return Vehicle
     */
    public Options getOptionParPosition(int position)
    {
        return listOption.get(position);

    }


    /**
     * ViewHolder.
     */
    class VehicleViewHolder extends RecyclerView.ViewHolder
    {
        TextView optionText = null;

        VehicleViewHolder(final View itemView) {
            super(itemView);

            optionText = itemView.findViewById(R.id.option_text);

            Switch optionSwitch = itemView.findViewById(R.id.option_switch);

            optionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Options clickOption = listOption.get(getAdapterPosition());

                    if (isChecked) {
                        optionsPrix = optionsPrix + clickOption.prix;
                    } else {
                        optionsPrix = optionsPrix - clickOption.prix;
                    }
                    bookingStep1Activity.optionPriceChange(optionsPrix);
                }
            });
        }

    }
}
