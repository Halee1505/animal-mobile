package com.funix.animal.ui.mammals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MammalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MammalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mammals fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}