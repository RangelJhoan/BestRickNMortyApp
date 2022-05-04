package com.example.bestricknmortyapp.iu.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.bestricknmortyapp.adapters.CharactersAdapter;
import com.example.bestricknmortyapp.databinding.FragmentCharacterBinding;
import com.example.bestricknmortyapp.entities.Character;
import com.example.bestricknmortyapp.iu.character.getCharactersList.GetCharactersListMVP;
import com.example.bestricknmortyapp.iu.character.getCharactersList.GetCharactersListPresenterImpl;
import com.example.bestricknmortyapp.utilities.Utilities;

import java.util.ArrayList;

public class CharacterFragment extends Fragment implements GetCharactersListMVP.View {

    private FragmentCharacterBinding binding;
    private GetCharactersListMVP.Presenter presenter;
    private String nextPageUrl = "null";
    private String previousPageUrl = "null";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new GetCharactersListPresenterImpl(this);
        presenter.getCharactersList(getContext(), Utilities.URL_CHARACTERS);
        binding.btnNext.setOnClickListener(view1 -> {
            presenter.getCharactersList(getContext(), nextPageUrl);
        });
        binding.btnPrevious.setOnClickListener(view1 -> {
            presenter.getCharactersList(getContext(), previousPageUrl);
        });
    }

    @Override
    public void showCharactersList(ArrayList<Character> characters, String nextPage, String previousPage) {
        binding.rvCharacters.setLayoutManager(new GridLayoutManager(getContext(), 2));
        CharactersAdapter adapter = new CharactersAdapter(characters, getContext());
        binding.rvCharacters.setAdapter(adapter);
        validateButtons(nextPage, previousPage);
    }

    private void validateButtons(String nextPage, String previousPage) {
        nextPageUrl = nextPage;
        previousPageUrl = previousPage;
        binding.btnNext.setEnabled(!nextPage.equals("null"));
        binding.btnPrevious.setEnabled(!previousPage.equals("null"));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}