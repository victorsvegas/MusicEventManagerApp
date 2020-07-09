package com.example.gruppprojektet_musicapp.ui.addConcert;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddConcertViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddConcertViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Add concert fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}
