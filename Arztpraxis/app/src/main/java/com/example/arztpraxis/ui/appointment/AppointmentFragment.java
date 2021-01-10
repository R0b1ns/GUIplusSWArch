package com.example.arztpraxis.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.ui.prescription.PrescriptionViewModel;
import com.example.arztpraxis.ui.prescription.PrescriptionViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AppointmentFragment extends Fragment {

    private AppointmentViewModel appointmentViewModel;
    private AppointmentNewFragment appointmentNewFragment;
    private AppointmentDetailFragment appointmentDetailFragment;
    private ArrayAdapter <String> model;
    private ArrayList<String> alItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ///appointmentViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);

        long id=((MyApplication) getActivity().getApplication()).getUserId();
        boolean isAdmin=((MyApplication) getActivity().getApplication()).isAdmin();

        appointmentViewModel = new ViewModelProvider(
                this,new AppointmentViewModelFactory(id,isAdmin)).get(AppointmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appointments, container, false);

        final ListView listView = root.findViewById(R.id.listView);

        alItems=new ArrayList<String>();
        model=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,alItems);

        listView.setAdapter(model);


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
                appointmentDetailFragment = AppointmentDetailFragment.newInstance(
                        Objects.requireNonNull(appointmentViewModel.getScheduleId().getValue())[position]);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, appointmentDetailFragment);
                ft.addToBackStack("appointmentDetail");
                ft.commit();
            }
        });

        FloatingActionButton addAppointmentBtn = root.findViewById(R.id.addAppointmentBtn);

        if(((MyApplication) getActivity().getApplication()).isLoggedIn() && ((MyApplication) getActivity().getApplication()).isAdmin()) {
            addAppointmentBtn.hide();
        }

        addAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_appointmentNew);
            }
        });

        return root;
    }
}