package com.example.gruppprojektet_musicapp.ui.ConcertInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConcertInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConcertInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is NewsInfo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}