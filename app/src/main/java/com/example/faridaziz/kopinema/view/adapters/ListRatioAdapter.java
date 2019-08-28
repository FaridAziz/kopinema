package com.example.faridaziz.kopinema.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.models.ItemRatio;

import java.util.ArrayList;

public class ListRatioAdapter extends RecyclerView.Adapter<ListRatioAdapter.Holder> {
    private ArrayList<ItemRatio> mpilihList;

    static class Holder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView1;
        TextView mTextView2;

        Holder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public ListRatioAdapter(ArrayList<ItemRatio> pilihList) {
        mpilihList = pilihList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ratio_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ItemRatio currentItem = mpilihList.get(position);

        holder.mImageView.setImageResource(currentItem.getImgResource());
        holder.mTextView1.setText(currentItem.getRatio());
        holder.mTextView2.setText(currentItem.getDetailRatio());
    }

    @Override
    public int getItemCount() {
        return mpilihList.size();
    }
}
