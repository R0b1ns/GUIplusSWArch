package com.example.arztpraxis.ui.prescription;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Disease;
import com.example.arztpraxis.model.Drug;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Prescription;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrescriptionCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrescriptionCreateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrescriptionCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrescriptionCreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrescriptionCreateFragment newInstance(String param1, String param2) {
        PrescriptionCreateFragment fragment = new PrescriptionCreateFragment();
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
        View root = inflater.inflate(R.layout.fragment_prescription_create, container, false);

        EditText etPrescriptionPatient= root.findViewById(R.id.prescriptionCreatePatient);
        EditText etPrescriptionDrug= root.findViewById(R.id.prescriptionCreateDrug);
        EditText etPrescriptionDisease= root.findViewById(R.id.prescriptionCreateDisease);

        Button btnCreatePrescription = root.findViewById(R.id.prescriptionCreateButton);
        btnCreatePrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncCreateNewPrescription().execute(
                        etPrescriptionPatient.getText().toString(),
                        etPrescriptionDrug.getText().toString(),
                        etPrescriptionDisease.getText().toString()
                );
            }
        });


        return root;
    }
    private class AsyncCreateNewPrescription extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            InfrastructureWebservice service = new InfrastructureWebservice();

            String patientName=strings[0];
            String drugName=strings[1];
            String diseaseName=strings[2];
            long employeeId=((MyApplication)getActivity().getApplication()).getUserId();
            Date date=Calendar.getInstance().getTime();
            long patientId=0;
            long drugId=0;
            long diseaseId=0;


            try {
                Collection<Person>persons= service.getAllPersons();
                boolean validPerson=false;
                for (Person person:persons){
                    if ((person.getFirstName()+" "+person.getLastName()).equals(patientName)){
                        validPerson=true;
                        patientId = service.getPatientOfName(person.getFirstName(),person.getLastName());
                        break;
                    }
                }
                if (!validPerson){
                    Snackbar.make(getView(), "Invalid Patient Name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }

                Collection<Drug>drugs= service.getAllDrugs();
                boolean validDrug=false;
                for (Drug drug:drugs){
                    System.out.println("1:"+drug.getDescription());
                    System.out.println("2:"+drugName);
                    if (drug.getDescription().equals(drugName)){
                        validDrug=true;
                        drugId=drug.getId();
                        break;
                    }
                }
                if (!validDrug){
                    Snackbar.make(getView(), "Invalid Drug Name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }

                Collection<Disease>diseases= service.getAllDiseases();
                boolean validDisease=false;
                for (Disease disease:diseases){
                    if (disease.getDescription().equals(diseaseName)){
                        validDisease=true;
                        diseaseId=disease.getId();
                        break;
                    }
                }
                if (!validDisease){
                    Snackbar.make(getView(), "Invalid Disease Name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }

                long presciptionId=service.getAllPrescriptions().size()+1;
                Prescription prescription= new Prescription(presciptionId,
                        (int)patientId,(int)employeeId,(int)drugId,(int)diseaseId,date);
                service.createPrescription(prescription);
                Snackbar.make(getView(), "Prescription created!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }catch(Exception e){
                Snackbar.make(getView(), "Something went wrong", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                e.printStackTrace();
            }




            return null;
        }
    }
}