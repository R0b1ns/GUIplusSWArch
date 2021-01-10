package com.example.arztpraxis.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;


import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button loginButton = root.findViewById(R.id.loginButton);
        EditText etName= root.findViewById(R.id.loginName);
        EditText etId=root.findViewById(R.id.loginID);
        RadioGroup rgType= root.findViewById(R.id.loginType);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rbType = root.findViewById(rgType.getCheckedRadioButtonId());
                String type= "patient";
                if (rbType.getId()==R.id.loginTypeEmployee){
                    type="employee";
                }
                new AsyncLogin().execute(
                        etName.getText().toString(),
                        etId.getText().toString(),
                        type
                );
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }



    private class AsyncLogin extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            InfrastructureWebservice service = new InfrastructureWebservice();
            String loginName=strings[0];
            String loginID=strings[1];
            String loginType=strings[2].toLowerCase();


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
                        ((MyApplication)getActivity().getApplication()).setPerson(service.getPerson(employee.getPersonId()));
                    }
                    else if (loginType.equals("patient")){
                        Patient patient = gson.fromJson(loginData,Patient.class);
                        ((MyApplication)getActivity().getApplication()).setPatient(patient);
                        ((MyApplication)getActivity().getApplication()).setPerson(service.getPerson(patient.getPerson()));
                    }

                    System.out.println("Springe weg von Hier");
                    Navigation.findNavController(getView()).navigate(R.id.nav_home);
                }

            } catch (ArrayIndexOutOfBoundsException e){
                Snackbar.make(getView(), "Invalid Name!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}
