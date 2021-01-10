package com.example.arztpraxis.ui.prescription;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Disease;
import com.example.arztpraxis.model.Drug;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Prescription;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.Treatment;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.sql.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrescriptionDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrescriptionDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private long mParam1;
    private String mParam2;

    public PrescriptionDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrescriptionDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrescriptionDetailFragment newInstance(long param1, String param2) {
        PrescriptionDetailFragment fragment = new PrescriptionDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_prescription_detail, container, false);

        new AsyncLoadPrescription().execute(root);

        return root;
    }

    private class AsyncLoadPrescription extends AsyncTask<View, Void, Void> {
        @Override
        protected Void doInBackground(View... views) {
            View root = views[0];
            InfrastructureWebservice service = new InfrastructureWebservice();
            Prescription prescription;
            Employee employee;
            Disease disease;
            Drug drug;
            Patient patient;
            Person person_patient;
            Person person_employee;

            final TextView tvDoctor = root.findViewById(R.id.prescriptionDoctor);
            final TextView tvPatient = root.findViewById(R.id.prescriptionPatient);
            final TextView tvDrug = root.findViewById(R.id.prescriptionDrug);
            final TextView tvDisease = root.findViewById(R.id.prescriptionDisease);
            final TextView tvDate = root.findViewById(R.id.prescriptionDate);


            try {
                prescription = service.getPrescription(mParam1);
                if (prescription != null) {
                    employee = service.getEmployee(prescription.getEmployeeId());
                    patient = service.getPatient(prescription.getPatientId());
                    disease = service.getDisease(prescription.getDiseaseId());
                    drug = service.getDrug(prescription.getDrugId());
                    tvDate.setText(Helper.formatDateTime(new Date(prescription.getPrescriptionDate().getTime()), false));
                    if (patient != null && employee != null && disease != null && drug != null) {
                        tvDisease.setText(disease.getDescription());
                        tvDrug.setText(drug.getDescription());
                        person_employee = service.getPerson(employee.getPersonId());
                        person_patient = service.getPerson(patient.getPerson());
                        if (person_employee != null && person_patient != null) {
                            tvDoctor.setText(person_employee.getFirstName() + " " + person_employee.getLastName());
                            tvPatient.setText(person_patient.getFirstName() + " " + person_patient.getLastName());
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}