package com.example.arztpraxis.ui.appointment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppointmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String[]> mAppointmentList;

    public AppointmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

        mAppointmentList = new MutableLiveData<>();
        String[] appointmentItems = {"10.01.2021 - Generelle Untersuchung", "20.01.2021 - Operation", "01.02.2021 - Deportation"};
        mAppointmentList.setValue(appointmentItems);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String[]> getAppointments() {
        return mAppointmentList;
    }
}