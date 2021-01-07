package com.example.arztpraxis.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.ws.InfrastructureWebservice;
import com.example.arztpraxis.ws.NoSuchRowException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView tvName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

//        tvName=root.findViewById(R.id.textViewName);
//        InfrastructureWebservice service = null;
//        service = new InfrastructureWebservice();
//        Person person;
//        try {
//            person = service.getPerson(2);
//            System.out.println("Status: in try");
//            if (person != null){
//                System.out.println("Status: in person!=null");
//                tvName.setText(person.getFirstName()+" "+person.getLastName());
//            }
//        } catch (NoSuchRowException e) {
//            tvName.setText("Keine Person gefunden");
//            System.out.println("in catch");
//        }

        new AsyncLoadPerson().execute(root);
        //tvName.setText("lel");

        return root;
    }

    private class AsyncLoadPerson extends AsyncTask<View,Void,Void>{
        @Override
        protected Void doInBackground(View... views) {
            View view=views[0];
            tvName=view.findViewById(R.id.textViewName);
            InfrastructureWebservice service = null;
            service = new InfrastructureWebservice();
            Person person;
            try {
                person = service.getPerson(1);
                System.out.println("Status: in try");
                if (person != null){
                    System.out.println("Status: in person!=null");
                    tvName.setText(person.getFirstName()+" "+person.getLastName());
                }
            } catch (NoSuchRowException e) {
                tvName.setText("Keine Person gefunden");
                System.out.println("Status: in catch");
            }
            return null;
        }
    }

}