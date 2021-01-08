package com.example.arztpraxis.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AppointmentFragment extends Fragment {

    private AppointmentViewModel appointmentViewModel;
    private AppointmentNewFragment appointmentNewFragment;
    private AppointmentDetailFragment appointmentDetailFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appointmentViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appointments, container, false);

        final ListView listView = root.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                appointmentDetailFragment = AppointmentDetailFragment.newInstance(id);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, appointmentDetailFragment);
                ft.addToBackStack("appointmentDetail");
                ft.commit();
            }
        });

        appointmentViewModel.getAppointments().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] appointmentItems) {
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        appointmentItems
                );
                listView.setAdapter(listViewAdapter);
            }
        });

        FloatingActionButton addAppointmentBtn = root.findViewById(R.id.addAppointmentBtn);
        addAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appointmentNewFragment = new AppointmentNewFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, appointmentNewFragment, "appointmentNew");
                ft.addToBackStack("appointmentNew");
                ft.commit();
            }
        });

        return root;
    }
}