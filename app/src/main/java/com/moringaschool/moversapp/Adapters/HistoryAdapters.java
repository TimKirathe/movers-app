package com.moringaschool.moversapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.ui.InvoiceActivity;

import java.util.List;

public class HistoryAdapters extends RecyclerView.Adapter<HistoryAdapters.ViewHolder> {
Context context;
List<Booking> bookingList;

public HistoryAdapters(Context context, List<Booking> bookingList) {
    this.context = context;
    this.bookingList = bookingList;
}
    @Override
    public HistoryAdapters.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflate =LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.history_strip, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapters.ViewHolder holder, int position) {
        holder.textView1.setText(bookingList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
    return bookingList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        Button   button;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 =itemView.findViewById(R.id.movingDateTextView);
            button =itemView.findViewById(R.id.delete);
            cardView =itemView.findViewById(R.id.cardview);
        }
    }
}
