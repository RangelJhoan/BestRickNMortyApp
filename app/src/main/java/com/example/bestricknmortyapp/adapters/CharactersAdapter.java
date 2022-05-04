package com.example.bestricknmortyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bestricknmortyapp.R;
import com.example.bestricknmortyapp.entities.Character;
import com.example.bestricknmortyapp.databinding.ItemGridCharactersBinding;

import java.util.ArrayList;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolderCharacters> {

    private ArrayList<Character> charactersList;
    private Context context;
    private ItemGridCharactersBinding binding;

    public CharactersAdapter(ArrayList<Character> charactersList, Context context) {
        this.charactersList = charactersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCharacters onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemGridCharactersBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new ViewHolderCharacters(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCharacters holder, int position) {
        Glide.with(context)
                .load(charactersList.get(position).getImage())
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivAvatar);
        holder.tvName.setText(charactersList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return charactersList != null ? charactersList.size() : 0;
    }

    public class ViewHolderCharacters extends RecyclerView.ViewHolder {
        final private ImageView ivAvatar;
        final private TextView tvName;
        public ViewHolderCharacters(ItemGridCharactersBinding binding) {
            super(binding.getRoot());
            ivAvatar = binding.ivAvatar;
            tvName = binding.tvName;
        }
    }
}
