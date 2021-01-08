package com.example.arztpraxis.ui.patient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PatientViewModel extends ViewModel {
    private MutableLiveData<String[]> mPatientList;

    public PatientViewModel() {
        mPatientList = new MutableLiveData<>();
        String[] patientItems = {"Patient A", "Patient B"};
        mPatientList.setValue(patientItems);
    }

    public LiveData<String[]> getPatients() {
        return mPatientList;
    }
}