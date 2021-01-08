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
        appointmentViewModel =
                new ViewModelProvider(this).get(AppointmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appointments, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);

        //Preload via Database / XHRequest
        String[] appointmentItems = {"10.01.2021 - Generelle Untersuchung", "20.01.2021 - Operation", "01.02.2021 - Deportation"};

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

        AsyncLoadSchedulePatient myAsync = new AsyncLoadSchedulePatient();
        myAsync.execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Open List View Element with id: "+id, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                appointmentDetailFragment = new AppointmentDetailFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, appointmentNewFragment);
            }
        });


        FloatingActionButton addAppointmentBtn = root.findViewById(R.id.addAppointmentBtn);
        addAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Open AppointmentNewFragment", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                //getLayoutInflater().inflate(R.layout.fragment_appointment_new, container);
                appointmentNewFragment = new AppointmentNewFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, appointmentNewFragment);
                ft.commit();
            }
        });

        appointmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });
        return root;
    }

    //doesnt work, do runOnUiThread again?
    private void addToModel(String s){



        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                model.add(s);
                model.notifyDataSetChanged();
            }
        });
    }

    private class AsyncLoadSchedulePatient extends AsyncTask<Void,Void,String[]> {
        @Override
        protected String[] doInBackground(Void...voids) {
            String[] appointmentItems=new String[1];

//            appointmentItems[0] = "10.01.2021 - Generelle Untersuchung";
//            appointmentItems[1] = "20.01.2021 - Operation";
//            appointmentItems[2] = "01.02.2021 - Deportation";

            InfrastructureWebservice service = null;
            service = new InfrastructureWebservice();
            Collection <Schedule> schedules;
            try {
                schedules = service.getSchedulePatient(1);
                //System.out.println(schedules.toString());
                if (schedules != null){
                    appointmentItems = new String[schedules.size()];
                    //System.out.println("Status: in person!=null");
                    Schedule[] schedulesArray =  schedules.toArray(new Schedule[schedules.size()]);
                    System.out.println(schedulesArray[0]);
                    for (int i=0; i<schedules.size(); i++) {
                        //System.out.println("im in the for")
                        addToModel(
                                helper.formatDateTime(schedulesArray[i].getDate(),
                                        false)+
                                " "+helper.formatDateTime(schedulesArray[i].getDate(),true));
                        //System.out.println("im through " + i + 1 + " loop(s)");
                    }
                }


            } catch (Exception e) {
                addToModel("Keine Termine gefunden");
                //e.printStackTrace();
            }



            return appointmentItems;
        }



    }

}