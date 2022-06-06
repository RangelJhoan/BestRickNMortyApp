package com.example.bestricknmortyapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestricknmortyapp.databinding.ItemGridEpisodesBinding;
import com.example.bestricknmortyapp.entities.Episode;

import java.util.ArrayList;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolderEpisodes> {

    private ArrayList<Episode> episodeList;
    private ItemGridEpisodesBinding binding;

    public EpisodesAdapter(ArrayList<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public ViewHolderEpisodes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemGridEpisodesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderEpisodes(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEpisodes holder, int position) {
        holder.tvName.setText(episodeList.get(position).getName());
        holder.tvAirDate.setText(episodeList.get(position).getAirDate());
    }

    @Override
    public int getItemCount() {
        return episodeList != null ? episodeList.size() : 0;
    }

    public class ViewHolderEpisodes extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvAirDate;
        public ViewHolderEpisodes(ItemGridEpisodesBinding binding) {
            super(binding.getRoot());
            tvName = binding.tvName;
            tvAirDate = binding.tvAirDate;
        }
    }
}
