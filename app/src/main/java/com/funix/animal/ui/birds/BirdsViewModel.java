package com.funix.animal.ui.birds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BirdsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BirdsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is birds fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}