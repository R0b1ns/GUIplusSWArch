package com.example.arztpraxis.ui.prescription;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PrescriptionViewModelFactory implements ViewModelProvider.Factory {
    private long mParam;
    private boolean mParamAdmin;

    public PrescriptionViewModelFactory(long param, boolean paramAdmin) {
        mParam = param;
        mParamAdmin = paramAdmin;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PrescriptionViewModel(mParam, mParamAdmin);
    }
}
