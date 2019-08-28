package com.example.faridaziz.kopinema.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.view_model.StatusViewModel;

public class StatusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) { return inflater.inflate(R.layout.fragment_status, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel
        StatusViewModel viewModel = ViewModelProviders.of(this)
                .get(StatusViewModel.class);

        // Binding View
        TextView status = (TextView) view.findViewById(R.id.txt_status);

        // Get Status Board
        viewModel.getStatus(status);
    }
}
