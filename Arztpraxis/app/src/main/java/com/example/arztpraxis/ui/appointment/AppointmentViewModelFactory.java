package com.example.arztpraxis.ui.appointment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AppointmentViewModelFactory implements ViewModelProvider.Factory {
    private long mParam;
    private boolean mParamAdmin;

    public AppointmentViewModelFactory(long param, boolean paramAdmin){
        mParam=param;
        mParamAdmin=paramAdmin;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AppointmentViewModel(mParam,mParamAdmin);
    }
}

