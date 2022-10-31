package com.Abhikash.covidtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<CityModel> cityModel;
    Context context;

    public CityAdapter(ArrayList<CityModel> cityModel, Context context) {
        this.cityModel = cityModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainactivityrow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String distname= cityModel.get(position).getDistrict();
        holder.district.setText(distname);
        holder.recovered.setText("Recovered: "+ cityModel.get(position).getRecovered());
        holder.deceased.setText("Deceased: "+ cityModel.get(position).getDeceased());
        holder.active.setText("Active: "+ cityModel.get(position).getActive());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,DataActivity.class);
                in.putExtra("distname",distname);
                context.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cityModel.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView district;
        public TextView active;
        public TextView recovered;
        public TextView deceased;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            district =(TextView) itemView.findViewById(R.id.state);
            active=(TextView) itemView.findViewById(R.id.total);
            recovered=(TextView) itemView.findViewById(R.id.recovered);
            deceased=(TextView) itemView.findViewById(R.id.deceased);
        }
    }
}
