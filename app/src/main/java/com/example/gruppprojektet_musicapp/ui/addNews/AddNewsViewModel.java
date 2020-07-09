package com.example.gruppprojektet_musicapp.ui.addNews;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddNewsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AddNewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is addArtist fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}
