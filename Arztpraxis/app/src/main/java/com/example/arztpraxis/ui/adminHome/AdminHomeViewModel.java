package com.example.arztpraxis.ui.adminHome;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.util.Collection;


public class AdminHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String[]>mScheduleRequest;
    private MutableLiveData<long[]>mScheduleRequestId;

    public AdminHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment für Terminanfragen");
        mScheduleRequest = new MutableLiveData<>();
        mScheduleRequest.setValue(new String[]{"no data found"});
        mScheduleRequestId = new MutableLiveData<>();
        mScheduleRequestId.setValue(new long[]{0});
        new AsyncLoadScheduleRequests().execute();

    }
    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String[]> getScheduleRequest() {
        return mScheduleRequest;
    }

    public MutableLiveData<long[]> getScheduleRequestId() {
        return mScheduleRequestId;
    }

    private class AsyncLoadScheduleRequests extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String[] requests;
            long[] requestIds;
            InfrastructureWebservice service = new InfrastructureWebservice();

            try{
                Collection<ScheduleRequest> scheduleRequests=service.getAllScheduleRequests();
                ScheduleRequest[] srArray =  scheduleRequests.toArray(new ScheduleRequest[scheduleRequests.size()]);
                requests= new String[scheduleRequests.size()];
                requestIds= new long[scheduleRequests.size()];
                for (int i=0;i<srArray.length;i++){
                    Employee employee= service.getEmployee(srArray[i].getEmployeeId());
                    Person person=service.getPerson(employee.getPersonId());
                    requests[i]="Priority: "+srArray[i].getPriority()+" Requests: "+
                            person.getFirstName()+" "+person.getLastName();
                    requestIds[i]=srArray[i].getId();
                }
                mScheduleRequest.postValue(requests);
                mScheduleRequestId.postValue(requestIds);

            }catch(Exception e){
                e.printStackTrace();
            }


            return null;
        }
    }
}
