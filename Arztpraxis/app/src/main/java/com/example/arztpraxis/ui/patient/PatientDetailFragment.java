package com.example.arztpraxis.ui.patient;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ui.prescription.PrescriptionCreateFragment;
import com.example.arztpraxis.ws.InfrastructureWebservice;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private PrescriptionCreateFragment prescriptionCreateFragment;

    private long mParam1;

    public PatientDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PatientDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientDetailFragment newInstance(long param1) {
        PatientDetailFragment fragment = new PatientDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_patient_detail, container, false);

        Button patientCreatePrescription = root.findViewById(R.id.patientCreatePrescription);
        patientCreatePrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prescriptionCreateFragment = new PrescriptionCreateFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, prescriptionCreateFragment, "prescriptionCreate");
                ft.addToBackStack("prescriptionCreate");
                ft.commit();
            }
        });

        new AsyncLoadPatient().execute();

        return root;
    }

    private class AsyncLoadPatient extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            InfrastructureWebservice service = new InfrastructureWebservice();

            TextView tvName = getView().findViewById(R.id.patientName);
            TextView tvBirthday = getView().findViewById(R.id.patientBirthdate);
            TextView tvGender = getView().findViewById(R.id.patientGender);
            TextView tvHealthInsurance = getView().findViewById(R.id.patientHealthInsuranceName);
            TextView tvHealthInsuranceNumber = getView().findViewById(R.id.patientHealthInsuranceNumber);


            try {
                Patient patient = service.getPatient(mParam1);
                if (patient != null) {

                    Person person = service.getPerson(patient.getPerson());

                    HealthInsurance healthInsurance = service.getHealthInsurance(patient.getHealthInsurance());

                    if (person != null && healthInsurance != null) {

                        tvName.setText(person.getFirstName() + " " + person.getLastName());
                        tvBirthday.setText(Helper.formatDateTime(person.getBirthday(), false));
                        tvGender.setText(Helper.getGender(person.getGender()));
                        tvHealthInsurance.setText(healthInsurance.getName());
                        tvHealthInsuranceNumber.setText(String.valueOf(patient.getSSN()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}