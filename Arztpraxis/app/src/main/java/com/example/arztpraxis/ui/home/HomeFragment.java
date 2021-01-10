package com.example.arztpraxis.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView tvName;
    private TextView tvBirthday;
    private TextView tvGender;
    private TextView tvHealthInsurance;
    private TextView tvHealthInsuranceNumber;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (((MyApplication) getActivity().getApplication()).isLoggedIn()){
            //((MyApplication) ((MyApplication) getActivity().getApplication()).setPatient());
        }else{
            //TODO: jump to settings;
        }

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        tvName=root.findViewById(R.id.textViewName);
        tvBirthday=root.findViewById(R.id.textViewBirthdate);
        tvGender=root.findViewById(R.id.textViewGender);
        tvHealthInsurance=root.findViewById(R.id.textViewHealthInsurance);
        tvHealthInsuranceNumber=root.findViewById(R.id.textViewHealthInsuranceNumber);


        homeViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvName.setText(s);
            }
        });

        homeViewModel.getBirthday().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvBirthday.setText(s);
            }
        });

        homeViewModel.getGender().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvGender.setText(s);
            }
        });

        homeViewModel.getHealthInsurance().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHealthInsurance.setText(s);
            }
        });

        homeViewModel.getHealthInsuranceNumber().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHealthInsuranceNumber.setText(s);
            }
        });

        homeViewModel.getPatient().observe(getViewLifecycleOwner(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                ((MyApplication)getActivity().getApplication()).setPatient(patient);
                ((MyApplication)getActivity().getApplication()).setLoggedIn(patient!=null);
                //TODO: remove below me in function
                ((MyApplication)getActivity().getApplication()).setEmployee(new Employee(1,1,55568,1));//hans maier
            }
        });

//        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("EEE,yyyy MM dd");//führt evtl später zu Problemen
//        Gson gson = gsonBuilder.create();
//
//        Employee employee = gson.fromJson("{id=1, position=1, personnalnumber=55568, personId=1}",Employee.class);
//        System.out.println(employee.toString());
//
//        Patient patient = gson.fromJson("{id=1, SSN=2456987, person=3, healthInsurance=1}",Patient.class);
//        System.out.println(patient.toString());


        return root;
    }
}