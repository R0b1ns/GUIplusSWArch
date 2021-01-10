package com.example.arztpraxis.ui.adminHome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.Helper;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.model.Treatment;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private long mParam1;

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
        final TextView tvDatetime = root.findViewById(R.id.appointmentRequestDate);
        final TextView tvDoctor = root.findViewById(R.id.appointmentRequestDoctor);
        final TextView tvAnnotation = root.findViewById(R.id.appointmentRequestAnnotation);
        final TextView tvPatient = root.findViewById(R.id.appointmentRequestPatient);
        final TextView tvPriority = root.findViewById(R.id.appointmentRequestPriority);



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

            return null;
        }
    }
}