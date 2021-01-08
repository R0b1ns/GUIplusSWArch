package com.example.arztpraxis.ui.prescription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;

public class PrescriptionFragment extends Fragment {

    private PrescriptionViewModel prescriptionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        prescriptionViewModel =
                new ViewModelProvider(this).get(PrescriptionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_prescription, container, false);

        //Preload via Database / XHRequest
        String[] prescriptionItems = {"Rezept XY", "ItemB", "ItemX"};

        final ListView listView = root.findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                prescriptionItems
        );

        listView.setAdapter(listViewAdapter);

        //final TextView textView = root.findViewById(R.id.text_slideshow);
        prescriptionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });
        return root;
    }
}