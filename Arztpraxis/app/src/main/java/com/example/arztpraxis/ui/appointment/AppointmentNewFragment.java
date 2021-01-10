package com.example.arztpraxis.ui.appointment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentNewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentNewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentNewFragment newInstance(String param1, String param2) {
        AppointmentNewFragment fragment = new AppointmentNewFragment();
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
        View root = inflater.inflate(R.layout.fragment_appointment_new, container, false);

        Button appointmentNewSubmit = root.findViewById(R.id.appointmentNewSubmit);
        appointmentNewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup appointmentNewPressure = root.findViewById(R.id.appointmentNewPressure);
                RadioButton selectedPressure = root.findViewById(appointmentNewPressure.getCheckedRadioButtonId());
                EditText requestedDoctor = root.findViewById(R.id.requestedDoctor);
                EditText appointmentNewAnnotation = root.findViewById(R.id.appointmentNewAnnotation);

                String pressure = "";

                switch (selectedPressure.getId()) {
                    case R.id.appointmentNewPressureLow:
                        pressure = "niedrig";
                        break;

                    case R.id.appointmentNewPressureMedium:
                        pressure = "mittel";
                        break;

                    case R.id.appointmentNewPressureHigh:
                        pressure = "hoch";
                        break;
                }

                new AsyncSendScheduleAppointment().execute(
                        new String[]{appointmentNewAnnotation.getText().toString(),
                                requestedDoctor.getText().toString(),
                                pressure});
            }
        });

        return root;
    }

    private class AsyncSendScheduleAppointment extends AsyncTask<String[],Void,Void> {
        @Override
        protected Void doInBackground(String[]... strings) {
            String s_annotation=strings[0][0];
            String[] s_doctor=strings[0][1].split(" ");
            String s_pressure=strings[0][2];

            if (s_doctor.length==2) {

                InfrastructureWebservice service = new InfrastructureWebservice();
                ScheduleRequest scheduleRequest;
                int scheduleRequestLength;

                try {
                    scheduleRequestLength = service.getAllScheduleRequests().size();
                    // last two values should be changed to something different, called with method
                    scheduleRequest = new ScheduleRequest(scheduleRequestLength + 1,
                            s_pressure, s_annotation,
                            (int) ((MyApplication) getActivity().getApplication()).getUserId(),
                            (int) service.getEmployeeOfName(s_doctor[0],s_doctor[1]));
                    service.createScheduleRequest(scheduleRequest);
                    Snackbar.make(getView(), "Appointment was requested successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Snackbar.make(getView(), "Something went wrong, sorry!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }else{
                Snackbar.make(getView(), "please enter a valid employee([First Name]SPACE[Last Name]", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            return null;
        }
    }
}