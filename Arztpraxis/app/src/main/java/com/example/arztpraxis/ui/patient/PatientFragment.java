package com.example.arztpraxis.ui.patient;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arztpraxis.R;
import com.example.arztpraxis.ui.appointment.AppointmentNewFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PatientFragment extends Fragment {

    private PatientViewModel mViewModel;
    private PatientAddFragment patientAddFragment;

    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient, container, false);

        FloatingActionButton patientAdd = root.findViewById(R.id.patientAdd);
        patientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientAddFragment = new PatientAddFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, patientAddFragment, "patientAdd");
                ft.addToBackStack("patientAdd");
                ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        // TODO: Use the ViewModel
    }

}