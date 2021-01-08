package com.example.arztpraxis.ui.patient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.arztpraxis.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientAddFragment newInstance(String param1, String param2) {
        PatientAddFragment fragment = new PatientAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_patient_add, container, false);

        Button patientAddSubmit = root.findViewById(R.id.patientAddSubmit);
        patientAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText patientFirstname = root.findViewById(R.id.patientFirstname);
                EditText patientLastname = root.findViewById(R.id.patientLastname);
                EditText patientBirthdate = root.findViewById(R.id.patientBirthdate);

                EditText patientHealthInsuranceName = root.findViewById(R.id.patientHealthInsuranceName);
                EditText patientHealthInsuranceNumber = root.findViewById(R.id.patientHealthInsuranceNumber);

                RadioGroup patientGenderRadio = root.findViewById(R.id.patientGender);
                RadioButton selectedPatientGender = root.findViewById(patientGenderRadio.getCheckedRadioButtonId());

                //Send request to server with this data
                patientFirstname.getText();
                patientLastname.getText();
                patientBirthdate.getText();
                patientHealthInsuranceName.getText();
                patientHealthInsuranceNumber.getText();
                selectedPatientGender.getText();

                //if sent successful
                Snackbar.make(v, "Patient was created successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return root;
    }
}