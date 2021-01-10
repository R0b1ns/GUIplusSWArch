package com.example.arztpraxis.ui.appointment;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.Treatment;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.util.Collection;

public class AppointmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String[]> mSchedule;
    private MutableLiveData<long[]> mScheduleId;

    private long id;
    private boolean isAdmin;

    public AppointmentViewModel(long id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

        mSchedule = new MutableLiveData<>();
        mSchedule.setValue(new String[]{"undefined"});

        mScheduleId = new MutableLiveData<>();
        mScheduleId.setValue(new long[]{0});

        new AsyncLoadScheduleAppointment().execute();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String[]> getSchedule() {
        return mSchedule;
    }

    public LiveData<long[]> getScheduleId() {
        return mScheduleId;
    }

    private class AsyncLoadScheduleAppointment extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String[] appointmentItems;

            InfrastructureWebservice service = null;
            service = new InfrastructureWebservice();
            Collection<Schedule> schedules;
            try {
                if (isAdmin) {
                    schedules = service.getScheduleEmployee(id);
                } else {
                    schedules = service.getSchedulePatient(id);
                }

                if (schedules != null) {
                    appointmentItems = new String[schedules.size()];
                    long[] appointmentIds = new long[schedules.size()];

                    Schedule[] schedulesArray = schedules.toArray(new Schedule[schedules.size()]);

                    for (int i = 0; i < schedules.size(); i++) {
                        Treatment t = service.getTreatment(schedulesArray[i].getTreatmentId());
                        appointmentIds[i] = schedulesArray[i].getId();
                        appointmentItems[i] =
                                Helper.formatDateTime(schedulesArray[i].getDate(), false) +
                                        " " +
                                        Helper.formatDateTime(schedulesArray[i].getDate(), true) +
                                        " : " + t.getDescription();
                    }
                    mSchedule.postValue(appointmentItems);
                    mScheduleId.postValue(appointmentIds);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

