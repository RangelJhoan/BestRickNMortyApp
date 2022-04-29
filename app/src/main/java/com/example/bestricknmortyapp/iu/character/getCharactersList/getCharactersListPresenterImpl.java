package com.example.bestricknmortyapp.iu.character.getCharactersList;

import android.content.Context;

import com.example.bestricknmortyapp.entities.Character;

import java.util.ArrayList;

public class getCharactersListPresenterImpl implements getCharactersListMVP.Presenter {

    private getCharactersListMVP.Model model;
    private getCharactersListMVP.View view;

    public getCharactersListPresenterImpl(getCharactersListMVP.View view) {
        this.view = view;
        this.model = new getCharactersListModelImpl(this);
    }

    @Override
    public void showCharactersList(ArrayList<Character> characters) {
        if(view != null){
            view.showCharactersList(characters);
        }
    }

    @Override
    public void showError(String error) {
        if(view != null){
            view.showError(error);
        }
    }

    @Override
    public void getCharactersList(Context context) {
        if(model != null){
            model.getCharactersList(context);
        }
    }
}
