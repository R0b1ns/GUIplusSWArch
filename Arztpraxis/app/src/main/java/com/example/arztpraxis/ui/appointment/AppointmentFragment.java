package com.example.arztpraxis.ui.appointment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.MainActivity;
import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.helper.helper;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.example.arztpraxis.ws.NoSuchRowException;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentFragment extends Fragment {

    private AppointmentViewModel appointmentViewModel;
    private AppointmentNewFragment appointmentNewFragment;
    private AppointmentDetailFragment appointmentDetailFragment;
    private ArrayAdapter <String> model;
    private ArrayList<String> alItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appointmentViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appointments, container, false);

        final ListView listView = root.findViewById(R.id.listView);

//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                appointmentItems
//        );
//
//        listView.setAdapter(listViewAdapter);


        alItems=new ArrayList<String>();
        model=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,alItems);

        listView.setAdapter(model);

//        AsyncLoadSchedulePatient myAsync = new AsyncLoadSchedulePatient();
//        myAsync.execute();
        appointmentViewModel.getSchedule().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                model.clear();
                for (int i=0; i<strings.length;i++) {
                    model.add(strings[i]);
                }
                model.notifyDataSetChanged();
            }
        });


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