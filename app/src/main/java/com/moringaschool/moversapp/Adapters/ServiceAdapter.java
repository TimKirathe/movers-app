package com.moringaschool.moversapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.models.Service;
import com.moringaschool.moversapp.ui.SourceMapActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>{
    Context context;
    ArrayList<Service> services;

    public ServiceAdapter(Context context, ArrayList<Service> services) {
        this.context = context;
        this.services = services;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.service_strip, parent,false);
        return new ServiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(services.get(position).getPhotoLink()).into(holder.imageView1);
        holder.textView1.setText("" + services.get(position).getPrice() + "Ksh.");
        holder.textView2.setText("Number of bedrooms: " + services.get(position).getNoOfBedRooms());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SourceMapActivity.class);
                intent.putExtra("Service", services.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView textView1;
        TextView textView2;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imagedisplay);
            textView1= itemView.findViewById(R.id.displayPrice);
            textView2 =itemView.findViewById(R.id.noOfRoomsDisplay);
            cardView = itemView.findViewById(R.id.cardView1);
        }


    }
}
