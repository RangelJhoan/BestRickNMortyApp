package com.example.bestricknmortyapp.iu.Episode.getEpisodesList;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bestricknmortyapp.entities.Episode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetEpisodesListModelImpl implements GetEpisodesListMVP.Model {

    private GetEpisodesListMVP.Presenter presenter;

    public GetEpisodesListModelImpl(GetEpisodesListMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEpisodesList(Context context, String url) {
        ArrayList<Episode> episodesList = new ArrayList<>();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                response -> {
                    Episode episode = null;
                    JSONObject info = response.optJSONObject("info");
                    JSONArray result = response.optJSONArray("results");
                    try {
                        if (result != null) {
                            for (int i = 0; i < result.length(); i++) {
                                episode = new Episode();
                                JSONObject episodeJSON = result.getJSONObject(i);

                                episode.setId(episodeJSON.getInt("id"));
                                episode.setName(episodeJSON.getString("name"));
                                episode.setAirDate(episodeJSON.getString("air_date"));
                                episode.setEpisode(episodeJSON.getString("episode"));
                                episode.setUrl(episodeJSON.getString("url"));
                                episode.setCreated(episodeJSON.getString("created"));

                                episodesList.add(episode);
                            }
                        }

                        assert info != null;
                        presenter.showEpisodesList(episodesList, info.getString("next"), info.getString("prev"));
                    } catch (Exception e) {
                        Log.e("getEpisodesModel", e.getMessage());
                        presenter.showError("Error al mostrar los episodios");
                    }
                },
                error -> {
                    Log.e("getEpisodesModel", error.getMessage());
                    presenter.showError("Error al consultar los episodios");
                });
        requestQueue.add(request);
    }
}
