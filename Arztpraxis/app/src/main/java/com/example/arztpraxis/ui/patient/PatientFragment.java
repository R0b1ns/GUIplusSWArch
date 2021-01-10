package com.example.arztpraxis.ui.patient;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.ui.appointment.AppointmentNewFragment;
import com.example.arztpraxis.ui.appointment.AppointmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PatientFragment extends Fragment {

    private PatientViewModel patientViewModel;
    private PatientDetailFragment patientDetailFragment;

    private long[] patientId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        if (((MyApplication) getActivity().getApplication()).isLoggedIn() && !((MyApplication) getActivity().getApplication()).isAdmin()) {
            return inflater.inflate(R.layout.fragment_no_admin, container, false);
        }

        View root = inflater.inflate(R.layout.fragment_patient, container, false);

        final ListView patientList = root.findViewById(R.id.patientList);
        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (patientId[0] > 0) {
                    patientDetailFragment = PatientDetailFragment.newInstance(patientId[position]);
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, patientDetailFragment);
                    ft.addToBackStack("patientDetail");
                    ft.commit();
                }
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

        patientViewModel.getPatientId().observe(getViewLifecycleOwner(), new Observer<long[]>() {
            @Override
            public void onChanged(long[] longs) {
                patientId = longs;
            }
        });

        FloatingActionButton patientAdd = root.findViewById(R.id.patientAdd);
        patientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_patientAdd);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

    }

}