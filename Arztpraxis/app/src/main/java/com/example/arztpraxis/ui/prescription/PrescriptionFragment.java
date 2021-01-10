package com.example.arztpraxis.ui.prescription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Prescription;
import com.example.arztpraxis.ui.appointment.AppointmentDetailFragment;

import java.util.Objects;

public class PrescriptionFragment extends Fragment {

    private PrescriptionViewModel prescriptionViewModel;
    private PrescriptionDetailFragment prescriptionDetailFragment;
    private long[] prescriptionId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        long id = ((MyApplication) getActivity().getApplication()).getUserId();
        boolean isAdmin = ((MyApplication) getActivity().getApplication()).isAdmin();

        prescriptionViewModel = new ViewModelProvider(
                this, new PrescriptionViewModelFactory(id, isAdmin)).get(PrescriptionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_prescription, container, false);

        final ListView listView = root.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prescriptionDetailFragment = PrescriptionDetailFragment.newInstance(
                        prescriptionId[position], null);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, prescriptionDetailFragment);
                ft.addToBackStack("prescriptionDetail");
                ft.commit();
            }
        });

        prescriptionViewModel.getPrescriptionStringList().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] prescriptionItems) {
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        prescriptionItems
                );
                listView.setAdapter(listViewAdapter);
            }
        });

        prescriptionViewModel.getPrescriptionId().observe(getViewLifecycleOwner(), new Observer<long[]>() {
            @Override
            public void onChanged(long[] longs) {
                prescriptionId = longs;
            }
        });

        return root;
    }


}