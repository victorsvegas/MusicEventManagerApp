package com.example.gruppprojektet_musicapp.ui.addArtist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddArtistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddArtistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is addArtist fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}
