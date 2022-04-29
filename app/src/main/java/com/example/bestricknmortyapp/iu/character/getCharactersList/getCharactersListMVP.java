package com.example.bestricknmortyapp.iu.character.getCharactersList;

import android.content.Context;

import com.example.bestricknmortyapp.entities.Character;

import java.util.ArrayList;

public interface getCharactersListMVP {
    interface View{
        void showCharactersList(ArrayList<Character> characters);
        void showError(String error);
    }
    interface Presenter{
        void showCharactersList(ArrayList<Character> characters);
        void showError(String error);
        void getCharactersList(Context context);
    }
    interface Model{
        void getCharactersList(Context context);
    }
}
