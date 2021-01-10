package com.example.arztpraxis.ui.patient;

import android.os.AsyncTask;
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
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Adress;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

                EditText patientAddressPLZ = root.findViewById(R.id.patientAddressPLZ);
                EditText patientAddressCity = root.findViewById(R.id.patientAddressCity);
                EditText patientAddressStreet = root.findViewById(R.id.patientAddressStreet);
                EditText patientAddressNumber = root.findViewById(R.id.patientAddressNumber);

                String gender="";

                switch (selectedPatientGender.getId()){
                    case R.id.patientGenderMale:
                        gender="male";
                        break;
                    case R.id.patientGenderFemale:
                        gender="female";
                        break;
                    case R.id.patientGenderOther:
                        gender="other";

                }

                new AsyncCreatePatient().execute(
                        patientFirstname.getText().toString(),
                        patientLastname.getText().toString(),
                        patientBirthdate.getText().toString(),
                        gender,
                        patientHealthInsuranceName.getText().toString(),
                        patientHealthInsuranceNumber.getText().toString(),
                        patientAddressPLZ.getText().toString(),
                        patientAddressCity.getText().toString(),
                        patientAddressStreet.getText().toString(),
                        patientAddressNumber.getText().toString()
                );
            }
        });

        return root;
    }

    private class AsyncCreatePatient extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... string) {
            InfrastructureWebservice service = new InfrastructureWebservice();
            String firstName = string[0];
            String lastName = string[1];
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date birthday = format.parse(string[2]);
                String gender = Helper.getGender(string[3]);

                String healthInsuranceName=string[4];
                int ssn=Integer.parseInt(string[5]);
                int address_plz=Integer.parseInt(string[6]);
                String address_city=string[7];
                String address_street=string[8];
                String address_number=string[9];
                boolean validHealthInsurance=false;
                int healthInsuranceId=0;
                for (HealthInsurance healthInsurance: service.getAllHealthInsurances()){
                    if(healthInsurance.getName().equals(healthInsuranceName)){
                        validHealthInsurance=true;
                        healthInsuranceId=(int)healthInsurance.getId();
                        break;
                    }
                }
                if(!validHealthInsurance){
                    Snackbar.make(getView(), "Please enter valid Health-Insurance", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }

                long newAddressId=service.getAllAdresses().size()+1;
                long newPersonId=service.getAllPersons().size()+1;
                long newPatientId=service.getAllPatients().size()+1;

                Adress adress = new Adress(newAddressId,
                        address_plz,address_city,address_street,address_number);
                service.createAdress(adress);

                Person person = new Person(newPersonId,firstName,lastName,
                        new java.sql.Date(birthday.getTime()),(int)newAddressId,gender);
                service.createPerson(person);

                Patient patient = new Patient(newPatientId,ssn,(int)newPersonId,healthInsuranceId);
                service.createPatient(patient);


                Snackbar.make(getView(), "Patient was created successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            } catch (ParseException e) {
                Snackbar.make(getView(), "Please enter Date as dd.mm.yyyy", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }catch (NumberFormatException e){
                Snackbar.make(getView(), "Please enter valid SSN and PLZ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}                                                                                                                                                                                                                                                                                                             