package com.example.arztpraxis.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.example.arztpraxis.ws.NoSuchRowException;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mName;
    private MutableLiveData<String> mBirthday;
    private MutableLiveData<String> mGender;
    private MutableLiveData<String> mHealthInsurance;
    private MutableLiveData<String> mHealthInsuranceNumber;
    private MutableLiveData<Patient> mPatient;

    private long id;

    public HomeViewModel(long id) {
        this.id=id;
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mName = new MutableLiveData<>();
        mName.setValue("no data found");
        mBirthday = new MutableLiveData<>();
        mBirthday.setValue("no data found");
        mGender = new MutableLiveData<>();
        mGender.setValue("no data found");
        mHealthInsurance = new MutableLiveData<>();
        mHealthInsurance.setValue("no data found");
        mHealthInsuranceNumber = new MutableLiveData<>();
        mHealthInsuranceNumber.setValue("no data found");
        mPatient = new MutableLiveData<>();
        mPatient.setValue(null);

        new AsyncLoadPerson().execute(this);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getBirthday() {
        return mBirthday;
    }

    public LiveData<String> getGender() {
        return mGender;
    }

    public LiveData<String> getHealthInsurance() {
        return mHealthInsurance;
    }

    public LiveData<String> getHealthInsuranceNumber() {
        return mHealthInsuranceNumber;
    }

    public LiveData<Patient> getPatient(){
        return mPatient;
    }



    private class AsyncLoadPerson extends AsyncTask<ViewModel, Void, Void> {
        @Override
        protected Void doInBackground(ViewModel...viewModels) {
            ViewModel viewModel=viewModels[0];

            InfrastructureWebservice service = null;
            service = new InfrastructureWebservice();
            Person person;
            Patient patient;
            HealthInsurance healthInsurance;
            try {
                if (id>0) {
                    patient = service.getPatient(id);
                    if (patient != null) {
                        //System.out.println(patient.toString());
                        person = service.getPerson(patient.getPerson());
                        //System.out.println(person.toString());
                        healthInsurance = service.getHealthInsurance(patient.getHealthInsurance());
                        //System.out.println(healthInsurance.toString());
                        if (person != null && healthInsurance != null) {
                            //System.out.println("Status: in person!=null");
                            mName.postValue(person.getFirstName() + " " + person.getLastName());
                            mBirthday.postValue(Helper.formatDateTime(person.getBirthday(), false));
                            mGender.postValue(Helper.getGender(person.getGender()));
                            mHealthInsurance.postValue(healthInsurance.getName());
                            mHealthInsuranceNumber.postValue(String.valueOf(patient.getSSN()));
                            mPatient.postValue(patient);
                        }
                    }
                }
            } catch (NoSuchRowException e) {
                //tvName.setText("Keine Person gefunden");
                e.printStackTrace();
            }
           return null;
        }
    }
}