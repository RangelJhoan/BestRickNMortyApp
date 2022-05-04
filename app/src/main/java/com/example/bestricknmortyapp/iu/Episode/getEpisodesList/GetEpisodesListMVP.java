package com.example.bestricknmortyapp.iu.Episode.getEpisodesList;

import android.content.Context;

import com.example.bestricknmortyapp.entities.Episode;

import java.util.ArrayList;

public interface GetEpisodesListMVP {
    interface View{
        void showEpisodesList(ArrayList<Episode> episodeList, String nextPage, String previousPage);
        void showError(String error);
    }
    interface Presenter{
        void showEpisodesList(ArrayList<Episode> episodeList, String nextPage, String previousPage);
        void showError(String error);
        void getEpisodesList(Context context, String url);
    }
    interface Model{
        void getEpisodesList(Context context, String url);
    }
}
