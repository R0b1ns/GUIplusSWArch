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
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.example.arztpraxis.ws.NoSuchRowException;
import com.example.arztpraxis.helper.helper;

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

//        tvName=root.findViewById(R.id.textViewName);
//        InfrastructureWebservice service = null;
//        service = new InfrastructureWebservice();
//        Person person;
//        try {
//            person = service.getPerson(2);
//            System.out.println("Status: in try");
//            if (person != null){
//                System.out.println("Status: in person!=null");
//                tvName.setText(person.getFirstName()+" "+person.getLastName());
//            }
//        } catch (NoSuchRowException e) {
//            tvName.setText("Keine Person gefunden");
//            System.out.println("in catch");
//        }

        //new AsyncLoadPerson().execute(root);
        //tvName.setText("lel");

        return root;
    }

    private class AsyncLoadPerson extends AsyncTask<View,Void,Void>{
        @Override
        protected Void doInBackground(View... views) {
            View view=views[0];
            tvName=view.findViewById(R.id.textViewName);
            tvBirthday=view.findViewById(R.id.textViewBirthdate);
            tvGender=view.findViewById(R.id.textViewGender);
            tvHealthInsurance=view.findViewById(R.id.textViewHealthInsurance);
            tvHealthInsuranceNumber=view.findViewById(R.id.textViewHealthInsuranceNumber);
            InfrastructureWebservice service = null;
            service = new InfrastructureWebservice();
            Person person;
            Patient patient;
            HealthInsurance healthInsurance;
            try {
                patient = service.getPatient(1);

                //System.out.println(patient.toString());
                person = service.getPerson(patient.getPerson());
                //System.out.println(person.toString());
                healthInsurance = service.getHealthInsurance(patient.getHealthInsurance());
                //System.out.println(healthInsurance.toString());
                if (person != null){
                    //System.out.println("Status: in person!=null");
                    tvName.setText(person.getFirstName()+" "+person.getLastName());
                    tvBirthday.setText(helper.formatDateTime(person.getBirthday(),false));
                    tvGender.setText(helper.getGender(person.getGender()));
                    tvHealthInsurance.setText(healthInsurance.getName());
                    tvHealthInsuranceNumber.setText(String.valueOf(patient.getSSN()));
                }
            } catch (NoSuchRowException e) {
                tvName.setText("Keine Person gefunden");
            }
            return null;
        }
    }

}