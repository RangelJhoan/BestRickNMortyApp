package com.example.bestricknmortyapp.iu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bestricknmortyapp.R;
import com.example.bestricknmortyapp.databinding.FragmentEpisodeBinding;

public class EpisodeFragment extends Fragment {

    private FragmentEpisodeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}