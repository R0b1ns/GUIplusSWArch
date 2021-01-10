package com.example.arztpraxis.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.ui.prescription.PrescriptionViewModel;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private long id;

    public HomeViewModelFactory(long id) {
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(id);
    }
}