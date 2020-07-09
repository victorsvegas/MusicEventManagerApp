package com.example.gruppprojektet_musicapp.ui.concerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConcertsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConcertsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}