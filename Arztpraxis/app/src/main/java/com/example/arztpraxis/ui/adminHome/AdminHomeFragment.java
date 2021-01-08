package com.example.arztpraxis.ui.adminHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.arztpraxis.R;

import java.util.ArrayList;

public class AdminHomeFragment extends Fragment {

    private AdminHomeViewModel adminHomeViewModel;
    private ArrayAdapter <String> model;
    private ArrayList<String> alItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminHomeViewModel =
                new ViewModelProvider(this).get(AdminHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_adminhome, container, false);



        final ListView listView = root.findViewById(R.id.listView);

        alItems=new ArrayList<String>();
        model=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,alItems);

        listView.setAdapter(model);

        return root;
    }

}
