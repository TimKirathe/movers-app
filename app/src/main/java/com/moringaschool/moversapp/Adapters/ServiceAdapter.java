package com.moringaschool.moversapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.models.Service;
import com.moringaschool.moversapp.ui.InvoiceActivity;
import com.moringaschool.moversapp.ui.SourceMapActivity;

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
        LayoutInflater inflate =LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.history_strip,parent,false);
        return new ServiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ServiceAdapter.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SourceMapActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView textView1;
        TextView textView2;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 =itemView.findViewById(R.id.imagedisplay);
            textView2 =itemView.findViewById(R.id.textdisplay);
            cardView =itemView.findViewById(R.id.cardview);
        }


    }
}
