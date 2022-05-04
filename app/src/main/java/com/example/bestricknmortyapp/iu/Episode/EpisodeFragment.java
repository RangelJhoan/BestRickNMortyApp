package com.example.bestricknmortyapp.iu.Episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bestricknmortyapp.R;
import com.example.bestricknmortyapp.adapters.EpisodesAdapter;
import com.example.bestricknmortyapp.databinding.FragmentEpisodeBinding;
import com.example.bestricknmortyapp.entities.Episode;
import com.example.bestricknmortyapp.iu.Episode.getEpisodesList.GetEpisodesListMVP;
import com.example.bestricknmortyapp.iu.Episode.getEpisodesList.GetEpisodesPresenterImpl;
import com.example.bestricknmortyapp.utilities.Utilities;

import java.util.ArrayList;

public class EpisodeFragment extends Fragment implements GetEpisodesListMVP.View {

    private FragmentEpisodeBinding binding;
    private GetEpisodesListMVP.Presenter presenter;
    private String nextPageUrl = "null";
    private String previousPageUrl = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new GetEpisodesPresenterImpl(this);
        presenter.getEpisodesList(getContext(), Utilities.URL_EPISODES);
        binding.btnNext.setOnClickListener(view1 -> {
            presenter.getEpisodesList(getContext(), nextPageUrl);
        });
        binding.btnPrevious.setOnClickListener(view1 -> {
            presenter.getEpisodesList(getContext(), previousPageUrl);
        });
    }

    @Override
    public void showEpisodesList(ArrayList<Episode> episodeList, String nextPage, String previousPage) {
        binding.rvEpisodes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        EpisodesAdapter adapter = new EpisodesAdapter(episodeList);
        binding.rvEpisodes.setAdapter(adapter);
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