package com.example.arztpraxis.ui.patient;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arztpraxis.R;
import com.example.arztpraxis.ui.appointment.AppointmentNewFragment;
import com.example.arztpraxis.ui.appointment.AppointmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PatientFragment extends Fragment {

    private PatientViewModel patientViewModel;
    private PatientAddFragment patientAddFragment;
    private PatientDetailFragment patientDetailFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        View root = inflater.inflate(R.layout.fragment_patient, container, false);

        final ListView patientList = root.findViewById(R.id.patientList);
        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                patientDetailFragment = PatientDetailFragment.newInstance(id);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, patientDetailFragment);
                ft.addToBackStack("patientDetail");
                ft.commit();
            }
        });

        patientViewModel.getPatients().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] patientItems) {
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        patientItems
                );
                patientList.setAdapter(listViewAdapter);
            }
        });

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
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        // TODO: Use the ViewModel
    }

}