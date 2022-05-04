package com.example.bestricknmortyapp.iu.character.getCharactersList;

import android.content.Context;

import com.example.bestricknmortyapp.entities.Character;

import java.util.ArrayList;

public class GetCharactersListPresenterImpl implements GetCharactersListMVP.Presenter {

    private GetCharactersListMVP.Model model;
    private GetCharactersListMVP.View view;

    public GetCharactersListPresenterImpl(GetCharactersListMVP.View view) {
        this.view = view;
        this.model = new GetCharactersListModelImpl(this);
    }

    @Override
    public void showCharactersList(ArrayList<Character> characters, String nextPage, String previousPage) {
        if(view != null){
            view.showCharactersList(characters, nextPage, previousPage);
        }
    }

    @Override
    public void showError(String error) {
        if(view != null){
            view.showError(error);
        }
    }

    @Override
    public void getCharactersList(Context context, String page) {
        if(model != null){
            model.getCharactersList(context, page);
        }
    }
}
