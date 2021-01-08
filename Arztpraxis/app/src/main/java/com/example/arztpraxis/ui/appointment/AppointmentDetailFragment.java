package com.example.arztpraxis.ui.appointment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arztpraxis.R;

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
        datetime.setText("01.01.2021 - 15:00:00");
        doctor.setText("Dr. Tor");
        annotation.setText("Bitte lade Informationen via XHR");

        return root;
    }
}