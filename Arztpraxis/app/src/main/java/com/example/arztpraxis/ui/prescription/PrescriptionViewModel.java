package com.example.arztpraxis.ui.prescription;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.model.Drug;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Prescription;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.util.Collection;

public class PrescriptionViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String[]> mPrescriptionStringList;
    private MutableLiveData<long[]> mPrescriptionId;

    private long userId = 0;
    private boolean isAdmin = false;

    public PrescriptionViewModel(long id, boolean isAdmin) {
        userId = id;
        this.isAdmin = isAdmin;

        mText = new MutableLiveData<>();
        mText.setValue("Fragment für Rezepte");
        mPrescriptionStringList = new MutableLiveData<>();
        mPrescriptionStringList.setValue(new String[]{"undefined"});
        mPrescriptionId = new MutableLiveData<>();
        mPrescriptionId.setValue(new long[]{0});

        new AsyncLoadPrescriptions().execute();

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String[]> getPrescriptionStringList() {
        return mPrescriptionStringList;
    }

    public LiveData<long[]> getPrescriptionId() {
        return mPrescriptionId;
    }

    private class AsyncLoadPrescriptions extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            InfrastructureWebservice service = new InfrastructureWebservice();
            Collection<Prescription> prescriptions;

            try {
                if (isAdmin) {
                    prescriptions = service.getPrescriptionFrom(userId);
                } else {
                    prescriptions = service.getPrescriptionOf(userId);
                }
                if (prescriptions != null) {
                    String[] prescriptionItems = new String[prescriptions.size()];
                    long[] prescriptionIds = new long[prescriptions.size()];
                    Prescription[] prescriptionArray = prescriptions.toArray(new Prescription[prescriptions.size()]);
                    for (int i = 0; i < prescriptions.size(); i++) {

                        Patient patient = service.getPatient(prescriptionArray[i].getPatientId());
                        Person person = service.getPerson(patient.getPerson());
                        Drug drug = service.getDrug(prescriptionArray[i].getDrugId());
                        prescriptionItems[i] = drug.getDescription() +
                                "(" + person.getFirstName() + " " + person.getLastName() + ") - " +
                                prescriptionArray[i].getPrescriptionDate().toString();
                        prescriptionIds[i] = prescriptionArray[i].getId();

                    }
                    mPrescriptionStringList.postValue(prescriptionItems);
                    mPrescriptionId.postValue(prescriptionIds);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}