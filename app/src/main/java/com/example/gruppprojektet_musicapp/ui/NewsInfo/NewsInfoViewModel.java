package com.example.gruppprojektet_musicapp.ui.NewsInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsInfoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public NewsInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is NewsInfo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
