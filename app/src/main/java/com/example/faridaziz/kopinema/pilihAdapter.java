package com.example.faridaziz.kopinema;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class pilihAdapter extends RecyclerView.Adapter<pilihAdapter.pilihViewHolder> {
    private ArrayList<pilihItem> mpilihList;

    public static class pilihViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public pilihViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);

        }
    }

    public pilihAdapter(ArrayList<pilihItem> pilihList) {
        mpilihList = pilihList;

    }

    @Override
    public pilihViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pilih_item, parent, false);
        pilihViewHolder pvh = new pilihViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(pilihViewHolder holder, int position) {
        pilihItem currentItem = mpilihList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mpilihList.size();
    }
}
