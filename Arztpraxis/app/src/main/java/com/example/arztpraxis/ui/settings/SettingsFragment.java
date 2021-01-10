package com.example.arztpraxis.ui.settings;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    private class AsyncLogin extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            InfrastructureWebservice service = new InfrastructureWebservice();
            String loginName=strings[0];
            String loginID=strings[1];
            String loginType=strings[2];


            try{
                String[] loginNames=loginName.split(" ");
                String loginFirstName=loginNames[0];
                String loginLastName=loginNames[1];
                String loginData=service.getLogin(loginType,loginFirstName,loginLastName,loginID);



                if (loginData!=null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    ((MyApplication)getActivity().getApplication()).setLoggedIn(true);

                    if (loginType.equals("employee")){
                        Employee employee = gson.fromJson(loginData,Employee.class);
                        ((MyApplication)getActivity().getApplication()).setEmployee(employee);
                    }
                    else{
                        Patient patient = gson.fromJson(loginData,Patient.class);
                        ((MyApplication)getActivity().getApplication()).setPatient(patient);
                    }
                    Snackbar.make(getView(), "Logged In!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }


            }catch (ArrayIndexOutOfBoundsException e){
                Snackbar.make(getView(), "Invalid Name!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }


            return  null;
        }
    }

}