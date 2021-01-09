package com.example.arztpraxis.ui.patient;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.util.Collection;

public class PatientViewModel extends ViewModel {
    private MutableLiveData<String[]> mPatientList;
    private MutableLiveData<long[]> mPatientId;

    public PatientViewModel() {
        mPatientList = new MutableLiveData<>();
        String[] patientItems = {"no data found"};
        mPatientList.setValue(patientItems);
        mPatientId = new MutableLiveData<>();
        mPatientId.setValue(new long[] {0});

        new AsyncLoadPatient().execute();
    }

    public LiveData<String[]> getPatients() {
        return mPatientList;
    }

    public LiveData<long[]> getPatientId(){
        return mPatientId;
    }

    private class AsyncLoadPatient extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            InfrastructureWebservice service = new InfrastructureWebservice();
            Collection<Patient> patients;

            try{
                patients=service.getAllPatients();
                if (patients!=null){
                    String[] patientItems=new String[patients.size()];
                    long[] patientIds= new long[patients.size()];
                    Patient[] patientsArray = patients.toArray(new Patient[patients.size()]);
                    for (int i=0; i<patients.size();i++){
                        Person person=service.getPerson(patientsArray[i].getPerson());
                        patientItems[i]=person.getFirstName()+" "+person.getLastName();
                        patientIds[i]=patientsArray[i].getId();
                    }
                    mPatientList.postValue(patientItems);
                    mPatientId.postValue(patientIds);
                }


            }catch (Exception e){

            }
            return null;
        }
    }
}