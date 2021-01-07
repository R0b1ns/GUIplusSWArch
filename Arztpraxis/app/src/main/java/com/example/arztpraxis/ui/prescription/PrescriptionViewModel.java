package com.example.arztpraxis.ui.prescription;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrescriptionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrescriptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment für Rezepte");
    }

    public LiveData<String> getText() {
        return mText;
    }
}