package com.example.faridaziz.kopinema.view.adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.models.ItemRatio;
import com.example.faridaziz.kopinema.view.fragments.RecommendationFragment;

import java.util.ArrayList;

public class ListRatioAdapter extends RecyclerView.Adapter<ListRatioAdapter.Holder> {
    private final String TAG = this.getClass().getSimpleName();
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
    public void onBindViewHolder(final Holder holder, int position) {
        ItemRatio currentItem = mpilihList.get(position);

        holder.mImageView.setImageResource(currentItem.getImgResource());
        holder.mTextView1.setText(currentItem.getRatio());
        holder.mTextView2.setText(currentItem.getDetailRatio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction fragment = activity.getSupportFragmentManager()
                        .beginTransaction();
                RecommendationFragment target = new RecommendationFragment();
                Bundle args = new Bundle();
                String[] datas = holder.mTextView1.getText().toString().split(":");
                ArrayList<Integer> lists = new ArrayList<>();

                // Convert from Array String to ArrayList Integer
                for (String data: datas) lists.add(Integer.valueOf(data));

                args.putIntegerArrayList(RecommendationFragment.ARG, lists);
                target.setArguments(args);
                fragment.replace(R.id.set_ratio_container, target)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpilihList.size();
    }
}
