package com.example.arztpraxis.ui.adminHome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.model.Treatment;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private long mParam1;

    private boolean validRequest;
    private long patient_id;
    private long employee_id;

    public AdminHomeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 ID of Appointment.
     * @return A new instance of fragment AppointmentDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminHomeDetailFragment newInstance(long param1) {
        AdminHomeDetailFragment fragment = new AdminHomeDetailFragment();
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
        View root = inflater.inflate(R.layout.fragment_adminhome_detail, container, false);

        final TextView tvId = root.findViewById(R.id.appointmentRequestID);
        final Button btnAccept = root.findViewById(R.id.appointmentRequestButton);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncAcceptAppointmentRequest().execute(root);
            }
        });


        //Fill data
        tvId.setText("Request: "+mParam1);


        new AsyncLoadAppointmentRequest().execute(root);

        return root;
    }

    private class AsyncLoadAppointmentRequest extends AsyncTask<View,Void,Void> {
        @Override
        protected Void doInBackground(View... views) {
            View root = views[0];
            InfrastructureWebservice service = new InfrastructureWebservice();

            final TextView tvPatient = root.findViewById(R.id.appointmentRequestPatient);
            final TextView tvDoctor = root.findViewById(R.id.appointmentRequestDoctor);
            final TextView tvAnnotation = root.findViewById(R.id.appointmentRequestAnnotation);
            final TextView tvPriority = root.findViewById(R.id.appointmentRequestPriority);

            ScheduleRequest scheduleRequest;
            Patient patient;
            Employee employee;
            Person person_patient;
            Person person_employee;
            String sPriority;




            try {
                scheduleRequest=service.getScheduleRequest(mParam1);
                if (scheduleRequest!=null){
                    switch (scheduleRequest.getPriority()){
                        case "hoch":
                            sPriority=getResources().getString(R.string.priority_high);
                            break;
                        case "mittel":
                            sPriority=getResources().getString(R.string.priority_medium);
                            break;
                        case "niedrig":
                            sPriority=getResources().getString(R.string.priority_low);
                            break;
                        default:
                            sPriority=getResources().getString(R.string.undefined);
                            break;
                    }
                    tvPriority.setText(sPriority);
                    tvAnnotation.setText(scheduleRequest.getNote());
                    patient=service.getPatient(scheduleRequest.getPatientId());
                    employee=service.getEmployee(scheduleRequest.getEmployeeId());
                    if (patient!=null&&employee!=null){
                        patient_id=patient.getId();
                        employee_id=employee.getId();
                        person_patient=service.getPerson(patient.getPerson());
                        person_employee=service.getPerson(employee.getPersonId());
                        if (person_patient!=null&&person_employee!=null){
                            tvPatient.setText(person_patient.getFirstName()+" "+person_patient.getLastName());
                            tvDoctor.setText(person_employee.getFirstName()+" "+person_employee.getLastName());
                            validRequest=true;

                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    private class AsyncAcceptAppointmentRequest extends AsyncTask<View,Void,Void> {
        @Override
        protected Void doInBackground(View... views) {
            View root = views[0];
            InfrastructureWebservice service = new InfrastructureWebservice();

            final EditText etDate = root.findViewById(R.id.appointmentRequestDate);
            final EditText etTime = root.findViewById(R.id.appointmentRequestTime);
            final EditText etTreatment = root.findViewById(R.id.appointmentRequestTreatment);



            if (validRequest){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String appointmentDate=etDate.getText().toString();
                    String appointmentTime=etTime.getText().toString();
                    String[] checkAppointmentDate=appointmentDate.split("\\.");
                    String[] checkAppointmentTime=appointmentTime.split(":");

                    if (checkAppointmentDate.length!=3||checkAppointmentDate[0].length()!=2||checkAppointmentDate[1].length()!=2||checkAppointmentDate[2].length()!=4){
                        Snackbar.make(getView(), "Date must be dd.MM.yyyy", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return null;
                    }

                    if (checkAppointmentTime.length!=2||checkAppointmentTime[0].length()!=2||checkAppointmentTime[1].length()!=2){
                        Snackbar.make(getView(), "Time must be HH:mm", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return null;
                    }


                    String appointmentDateTime = appointmentDate+" "+appointmentTime;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sdf.parse(appointmentDateTime));
                    Collection<Treatment> treatments = service.getAllTreatments();
                    boolean validTreatment = false;
                    long treatmentId = 0;

                    for (Treatment treatment : treatments) {

                        if (treatment.getDescription().equals(etTreatment.getText().toString())) {
                            validTreatment = true;
                            treatmentId = treatment.getId();
                        }
                    }

                    if (validTreatment) {
                        long next_id = service.getAllSchedule().size() + 1;
                        Schedule schedule = new Schedule(next_id, (int) patient_id, (int) employee_id, new java.sql.Date(calendar.getTimeInMillis()), (int) treatmentId);
                        service.createScheduleFromRequest(schedule, mParam1);
                    }
                    
                    Snackbar.make(getView(), "Appointment created!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //TODO: return to AdminHome
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(getView()).navigate(R.id.nav_home);
                        }
                    });

                }catch (ParseException e){
                    Snackbar.make(getView(), "Invalid Date or Time (use dd.MM.yyyy HH:mm)!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


            return null;
        }
    }
}