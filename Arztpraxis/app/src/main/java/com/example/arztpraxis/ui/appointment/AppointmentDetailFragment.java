package com.example.arztpraxis.ui.appointment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.Treatment;
import com.example.arztpraxis.ws.InfrastructureWebservice;

import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private long mParam1;

    public AppointmentDetailFragment() {
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
    public static AppointmentDetailFragment newInstance(long param1) {
        AppointmentDetailFragment fragment = new AppointmentDetailFragment();
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
        View root = inflater.inflate(R.layout.fragment_appointment_detail, container, false);

        final TextView title = root.findViewById(R.id.appointmentTitle);
        final TextView datetime = root.findViewById(R.id.datetime);
        final TextView doctor = root.findViewById(R.id.doctor);
        final TextView annotation = root.findViewById(R.id.annotation);

        //Fill data
        title.setText("Lade Termin mit ID: "+mParam1);
        datetime.setText("no data found");
        doctor.setText("no data found");
        annotation.setText("no data found");

        new AsyncLoadScheduleAppointment().execute(root);

        return root;
    }

    private class AsyncLoadScheduleAppointment extends AsyncTask<View,Void,Void> {
        @Override
        protected Void doInBackground(View... views) {
            View root = views[0];
            InfrastructureWebservice service = new InfrastructureWebservice();
            Schedule schedule;
            Employee employee;
            Treatment treatment;
            Person person;
            String employee_title;

            final TextView title = root.findViewById(R.id.appointmentTitle);
            final TextView datetime = root.findViewById(R.id.datetime);
            final TextView doctor = root.findViewById(R.id.doctor);
            final TextView annotation = root.findViewById(R.id.annotation);

            title.setText("Lade Termin mit ID: "+mParam1);

            try{
                schedule=service.getSchedule(mParam1);
                if (schedule!=null){
                    employee=service.getEmployee(schedule.getEmployeeId());
                    treatment=service.getTreatment(schedule.getTreatmentId());
                    if (employee!=null){
                        person=service.getPerson(employee.getPersonId());

                        datetime.setText(Helper.formatDateTime(schedule.getDate(),false)+
                                " "+Helper.formatDateTime(schedule.getDate(),true));
                        if (employee.getPosition()==1){
                            employee_title="Doctor";
                        }else{
                            switch(person.getGender()){
                                case "m":
                                    employee_title="Mr.";
                                    break;
                                case "w":
                                    employee_title="Ms.";
                                    break;
                                default:
                                    employee_title="Mx.";
                            }
                        }
                        doctor.setText(employee_title+" "+person.getFirstName()+" "+person.getLastName());
                        annotation.setText(treatment.getDescription());
                    }

                }

            }catch (Exception e){
                datetime.setText("no data found");
                doctor.setText("no data found");
                annotation.setText("no data found");
                e.printStackTrace();
            }

            return null;
        }
    }
}