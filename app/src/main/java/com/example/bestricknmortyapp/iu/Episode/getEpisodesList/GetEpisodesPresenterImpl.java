package com.example.bestricknmortyapp.iu.Episode.getEpisodesList;

import android.content.Context;

import com.example.bestricknmortyapp.entities.Episode;

import java.util.ArrayList;

public class GetEpisodesPresenterImpl implements GetEpisodesListMVP.Presenter{

    private GetEpisodesListMVP.Model model;
    private GetEpisodesListMVP.View view;

    public GetEpisodesPresenterImpl(GetEpisodesListMVP.View view) {
        this.view = view;
        this.model = new GetEpisodesListModelImpl(this);
    }

    @Override
    public void showEpisodesList(ArrayList<Episode> episodeList, String nextPage, String previousPage) {
        if(view != null){
            view.showEpisodesList(episodeList, nextPage, previousPage);
        }
    }

    @Override
    public void showError(String error) {
        if(view != null){
            view.showError(error);
        }
    }

    @Override
    public void getEpisodesList(Context context, String url) {
        if(model != null){
            model.getEpisodesList(context, url);
        }
    }
}
