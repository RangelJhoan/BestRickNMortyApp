package com.example.bestricknmortyapp.iu.character.getCharactersList;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bestricknmortyapp.entities.Character;
import com.example.bestricknmortyapp.entities.Location;
import com.example.bestricknmortyapp.entities.Origin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetCharactersListModelImpl implements GetCharactersListMVP.Model {

    private GetCharactersListMVP.Presenter presenter;

    public GetCharactersListModelImpl(GetCharactersListMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getCharactersList(Context context, String page) {
        ArrayList<Character> characters = new ArrayList<>();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                page,
                null,
                response -> {
                    Character character = null;
                    Location location = null;
                    Origin origin = null;
                    JSONObject info = response.optJSONObject("info");
                    JSONArray jsonArray = response.optJSONArray("results");
                    try {
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                character = new Character();
                                location = new Location();
                                origin = new Origin();
                                JSONObject characterJSON = jsonArray.getJSONObject(i);
                                JSONObject originJSON = characterJSON.getJSONObject("origin");
                                JSONObject locationJSON = characterJSON.getJSONObject("location");

                                character.setId(characterJSON.optInt("id"));
                                character.setName(characterJSON.getString("name"));
                                character.setStatus(characterJSON.getString("status"));
                                character.setSpecies(characterJSON.getString("species"));
                                character.setType(characterJSON.getString("type"));
                                character.setGender(characterJSON.getString("gender"));
                                origin.setName(originJSON.getString("name"));
                                origin.setUrl(originJSON.getString("url"));
                                location.setName(locationJSON.getString("name"));
                                location.setUrl(locationJSON.getString("url"));
                                character.setImage(characterJSON.getString("image"));
                                character.setUrl(characterJSON.getString("url"));
                                character.setCreated(characterJSON.getString("created"));
                                characters.add(character);
                            }
                        }
                        assert info != null;
                        presenter.showCharactersList(characters, info.getString("next"), info.getString("prev"));
                    } catch (Exception e) {
                        Log.e("getCharactersModel", e.getMessage());
                        presenter.showError("Error al mostrar los personajes");
                    }
                },
                error -> {
                    Log.e("getCharactersModel", error.getMessage());
                    presenter.showError("Error al consultar los personajes");
                }
        );
        requestQueue.add(request);
    }
}
