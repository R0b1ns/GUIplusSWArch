package com.example.arztpraxis.ui.home;

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
import androidx.navigation.Navigation;

import com.example.arztpraxis.R;
import com.example.arztpraxis.helper.MyApplication;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.ui.adminHome.AdminHomeDetailFragment;
import com.example.arztpraxis.ui.adminHome.AdminHomeViewModel;
import com.example.arztpraxis.ui.appointment.AppointmentDetailFragment;
import com.example.arztpraxis.ui.prescription.PrescriptionViewModel;
import com.example.arztpraxis.ui.prescription.PrescriptionViewModelFactory;
import com.example.arztpraxis.ui.settings.SettingsViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AdminHomeViewModel adminHomeViewModel;
    private AdminHomeDetailFragment adminHomeDetailFragment;

    private TextView tvName;
    private TextView tvBirthday;
    private TextView tvGender;
    private TextView tvHealthInsurance;
    private TextView tvHealthInsuranceNumber;

    private ArrayAdapter<String> model;
    private ArrayList<String> alItems;

    private long[] requestIdList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (((MyApplication) getActivity().getApplication()).isLoggedIn() && ((MyApplication) getActivity().getApplication()).isAdmin()) {
            return onCreateViewAdmin(inflater, container, savedInstanceState, inflater.inflate(R.layout.fragment_adminhome, container, false));
        }

        return onCreateViewDefault(inflater, container, savedInstanceState, inflater.inflate(R.layout.fragment_home, container, false));
    }

    private View onCreateViewDefault(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View root) {

        long id = ((MyApplication) getActivity().getApplication()).getUserId();

        homeViewModel = new ViewModelProvider(
                this, new HomeViewModelFactory(id)).get(HomeViewModel.class);

        tvName = root.findViewById(R.id.textViewName);
        tvBirthday = root.findViewById(R.id.textViewBirthdate);
        tvGender = root.findViewById(R.id.textViewGender);
        tvHealthInsurance = root.findViewById(R.id.textViewHealthInsurance);
        tvHealthInsuranceNumber = root.findViewById(R.id.textViewHealthInsuranceNumber);

        homeViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvName.setText(s);
            }
        });

        homeViewModel.getBirthday().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvBirthday.setText(s);
            }
        });

        homeViewModel.getGender().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvGender.setText(s);
            }
        });

        homeViewModel.getHealthInsurance().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHealthInsurance.setText(s);
            }
        });

        homeViewModel.getHealthInsuranceNumber().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHealthInsuranceNumber.setText(s);
            }
        });

        homeViewModel.getPatient().observe(getViewLifecycleOwner(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                ((MyApplication) getActivity().getApplication()).setPatient(patient);
                ((MyApplication) getActivity().getApplication()).setLoggedIn(patient != null);
            }
        });

        return root;
    }

    private View onCreateViewAdmin(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View root) {

        adminHomeViewModel = new ViewModelProvider(this).get(AdminHomeViewModel.class);

        final ListView listView = root.findViewById(R.id.listView);

        alItems = new ArrayList<String>();
        model = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, alItems);

        listView.setAdapter(model);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adminHomeDetailFragment = AdminHomeDetailFragment.newInstance(
                        requestIdList[position]);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, adminHomeDetailFragment);
                ft.addToBackStack("adminHomeDetail");
                ft.commit();
            }
        });

        adminHomeViewModel.getScheduleRequest().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        strings
                );
                listView.setAdapter(listViewAdapter);
            }
        });

        adminHomeViewModel.getScheduleRequestId().observe(getViewLifecycleOwner(), new Observer<long[]>() {
            @Override
            public void onChanged(long[] longs) {
                requestIdList = longs;
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!((MyApplication) getActivity().getApplication()).isLoggedIn()) {
            System.out.println("Not logged in");
            Navigation.findNavController(getView()).navigate(R.id.nav_settings);
        }

    }
}