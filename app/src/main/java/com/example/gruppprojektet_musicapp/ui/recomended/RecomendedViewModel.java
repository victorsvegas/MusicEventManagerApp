package com.example.gruppprojektet_musicapp.ui.recomended;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecomendedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecomendedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }


}