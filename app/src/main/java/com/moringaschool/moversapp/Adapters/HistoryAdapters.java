package com.moringaschool.moversapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.ui.InvoiceActivity;

public class HistoryAdapters extends RecyclerView.Adapter<HistoryAdapters.ViewHolder> {
Context context;
    @Override
    public HistoryAdapters.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflate =LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.history_strip,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapters.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InvoiceActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 =itemView.findViewById(R.id.movingfrom);
            textView2 =itemView.findViewById(R.id.movingto);
            textView3 =itemView.findViewById(R.id.amount);
            textView4 =itemView.findViewById(R.id.date);
            cardView =itemView.findViewById(R.id.cardview);
        }


    }
}
