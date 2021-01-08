package com.example.arztpraxis.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.example.arztpraxis.ws.NoSuchRowException;
import com.example.arztpraxis.helper.helper;
import com.google.gson.internal.$Gson$Preconditions;

import java.text.SimpleDateFormat;

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
        return root;
    }
}