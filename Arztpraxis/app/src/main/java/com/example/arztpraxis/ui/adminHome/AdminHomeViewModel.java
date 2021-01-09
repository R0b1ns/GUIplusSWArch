package com.example.arztpraxis.ui.adminHome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.ws.InfrastructureWebservice;


public class AdminHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String[]>mScheduleRequest;

    public AdminHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment für Terminanfragen");

    }
    public LiveData<String> getText() {
        return mText;
    }
}
